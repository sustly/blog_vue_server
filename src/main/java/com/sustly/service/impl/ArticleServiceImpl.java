package com.sustly.service.impl;

import com.sustly.dao.ArticleDao;
import com.sustly.entry.Blog;
import com.sustly.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liyue
 * @date 2019/6/20 15:46
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDao articleDao;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public void save(Blog blog) {
        articleDao.save(blog);
    }

    @Override
    public Blog findById(Integer id) {
        return articleDao.findById(id);
    }

    @Override
    public void delete(Integer id) {
        articleDao.deleteById(id);
    }
}
