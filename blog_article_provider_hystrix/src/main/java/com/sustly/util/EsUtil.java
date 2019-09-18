package com.sustly.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sustly.entry.Blog;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: liyue
 * @Date: 19-9-12 下午4:30
 */
@Component
@Slf4j
public class EsUtil {

    private final RestHighLevelClient restHighLevelClient;
    private static BasicHeader basicHeader = new BasicHeader("content-type", "application/json");
    private static final String ES_TYPE = "article";
    private static final String ES_INDEX = "article";

    @Autowired
    public EsUtil(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }


    @PostConstruct
    public void init() throws IOException {
        if (!existsIndex()) {
            CreateIndexRequest request = new CreateIndexRequest("article");
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request,RequestOptions.DEFAULT);
            log.info("createIndex: " + JSON.toJSONString(createIndexResponse));

        }
    }

    private boolean existsIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest();
        request.indices("article");
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        log.info("existsIndex: " + exists);
        return exists;
    }

    public void addData(Blog blog, String id) throws IOException {
        IndexRequest indexRequest = new IndexRequest(ES_INDEX, ES_TYPE, id);
        indexRequest.source(JSONObject.toJSONString(blog), XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("add: " + JSON.toJSONString(indexResponse));
    }

    public void updateDataById(Blog blog, String id) throws IOException {
        UpdateRequest request = new UpdateRequest(ES_INDEX, ES_TYPE, id);
        request.doc(JSONObject.toJSONString(blog), XContentType.JSON);
        UpdateResponse updateResponse = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        log.info("update: " + JSON.toJSONString(updateResponse));
    }

    public void deleteDataById(String id) throws IOException {
        DeleteRequest request = new DeleteRequest(ES_INDEX, ES_TYPE, id);
        DeleteResponse deleteResponse = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        log.info("delete: "+JSON.toJSONString(deleteResponse));
    }

    public List<Blog> searchDataPage(int startRow, int size, BoolQueryBuilder queryBuilder) throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(queryBuilder);
        sourceBuilder.from(startRow);
        // 获取记录数，默认10
        sourceBuilder.size(size);

        //设置高亮显示
        HighlightBuilder highlightBuilder = new HighlightBuilder().field("*").requireFieldMatch(false);
        highlightBuilder.preTags("<span style=\"color:red\">");
        highlightBuilder.postTags("</span>");
        sourceBuilder.highlighter(highlightBuilder);

        SearchRequest searchRequest = new SearchRequest(ES_INDEX);
        searchRequest.types(ES_TYPE);
        searchRequest.source(sourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //遍历结果
        for(SearchHit hit : response.getHits()){
            Map<String, Object> source = hit.getSourceAsMap();
            //处理高亮片段
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField nameField = highlightFields.get("title");
            if(nameField!=null){
                Text[] fragments = nameField.fragments();
                StringBuilder nameTmp = new StringBuilder();
                for(Text text:fragments){
                    nameTmp.append(text);
                }
                //将高亮片段组装到结果中去
                source.put("title", nameTmp.toString());
                log.info(source.toString());
            }
        }
        System.out.println("search: " + JSON.toJSONString(response));
        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();
        List<Blog> blogList = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            System.out.println("search -> " + hit.getSourceAsMap());
            JSONObject jsonObject = new JSONObject(hit.getSourceAsMap());
            blogList.add(JSONObject.toJavaObject(jsonObject, Blog.class));
        }
        return blogList;
    }
}

