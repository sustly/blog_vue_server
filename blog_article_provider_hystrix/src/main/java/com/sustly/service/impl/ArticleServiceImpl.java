package com.sustly.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sustly.dao.ArticleDao;
import com.sustly.dto.EsPage;
import com.sustly.entry.Blog;
import com.sustly.service.ArticleService;
import com.sustly.util.EsUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liyue
 * @date 2019/6/20 15:46
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class ArticleServiceImpl implements ArticleService {

    private final String ES_TYPE = "article";
    private final String ES_INDEX = "article";
    private final ArticleDao articleDao;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public void save(Blog blog) {
        String jsonString = JSONObject.toJSONString(blog);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        if (blog.getId() == null) {
            articleDao.save(blog);
            EsUtil.addData(jsonObject ,ES_INDEX, ES_TYPE, blog.getId().toString());
        }else {
            articleDao.update(blog);
            EsUtil.updateDataById(jsonObject ,ES_INDEX, ES_TYPE, blog.getId().toString());
        }
    }

    @Override
    public Blog findById(Integer id) {
        return articleDao.getBlogById(id);
    }

    @Override
    public void delete(Integer id) {
        articleDao.deleteById(id);
      //  repository.deleteByBlogId(id);
    }

    @Override
    public long getAllCount() {
        return articleDao.count();
    }

    @Override
    public List<Blog> getBlogListByPage(Integer page) {
        int startRow = page * 10;
        return articleDao.findAllPageByCreateTime(startRow, 10);
    }

    @Override
    public List<Blog> getBlogListByView(Integer page) {
        int startRow = page * 10;
        return articleDao.findAllPageByViews(startRow, 10);
    }

    @Override
    public long getAllCountByCategory(String category) {
        return articleDao.countByCategory(category);
    }

    @Override
    public List<Blog> getBlogListByCategory(Integer page, String category) {
        int startRow = page * 10;
        return articleDao.findAllPageByCategoryAndCreateTime(category, startRow, 10);
    }

    @Override
    public List<Blog> search(String search, Integer page) {
        int startRow = page * 10;
        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
        queryBuilder.must(QueryBuilders.matchQuery("title", search));
        queryBuilder.must(QueryBuilders.matchQuery("category", search));
        queryBuilder.must(QueryBuilders.matchQuery("content", search));
        EsPage esPage = EsUtil.searchDataPage(ES_INDEX, ES_TYPE, startRow, 10, queryBuilder, null, null, null);
        if (esPage == null){
            return null;
        }
        List<Map<String, Object>> recordList = esPage.getRecordList();
        List<Blog> blogList = new ArrayList<>();
        for (Map<String, Object> map:recordList) {
            Blog blog = JSON.parseObject(JSON.toJSONString(map), Blog.class);
            blogList.add(blog);
        }
        return blogList;
    }

    @Override
    public List<Blog> getBlogListByUsernameAndPage(Integer page, String username) {
        int startRow = page * 10;
        return articleDao.findAllPageByCategoryAndUsername(username, startRow, 10);
    }
}
