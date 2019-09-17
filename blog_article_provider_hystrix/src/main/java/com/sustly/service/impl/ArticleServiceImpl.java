package com.sustly.service.impl;

import com.alibaba.fastjson.JSON;
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

import java.io.IOException;
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

    private final ArticleDao articleDao;
    private final EsUtil esUtil;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao, EsUtil esUtil) {
        this.articleDao = articleDao;
        this.esUtil = esUtil;
    }

    @Override
    public void save(Blog blog) {
        if (blog.getId() == null) {
            articleDao.save(blog);
            try {
                esUtil.addData(blog, blog.getId().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            articleDao.update(blog);
            try {
                esUtil.updateDataById(blog, blog.getId().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Blog findById(Integer id) {
        return articleDao.getBlogById(id);
    }

    @Override
    public void delete(Integer id) {
        articleDao.deleteById(id);
        try {
            esUtil.deleteDataById(id.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        EsPage esPage = null;
        try {
            esPage = esUtil.searchDataPage(startRow, 10, queryBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
