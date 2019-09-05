package com.sustly.service.impl;

import com.sustly.dao.ArticleDao;
import com.sustly.dao.BlogElasticsearchRepository;
import com.sustly.document.EsBlog;
import com.sustly.entry.Blog;
import com.sustly.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liyue
 * @date 2019/6/20 15:46
 */
@Service
@Transactional(rollbackOn = {Exception.class})
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDao articleDao;
    private final BlogElasticsearchRepository repository;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao, BlogElasticsearchRepository repository) {
        this.articleDao = articleDao;
        this.repository = repository;
    }

    @Override
    public void save(Blog blog) {
        if (blog.getId() == null) {
            articleDao.save(blog);
            repository.save(new EsBlog(blog));
        }else {
            articleDao.update(blog);
            repository.save(new EsBlog(blog));
        }
    }

    @Override
    public Blog findById(Integer id) {
        return articleDao.getBlogById(id);
    }

    @Override
    public void delete(Integer id) {
        articleDao.deleteById(id);
        repository.deleteByBlogId(id);
    }

    @Override
    public long getAllCount() {
        return articleDao.count();
    }

    @Override
    public List<Blog> getBlogListByPage(Integer page) {
        int startRow = (page - 1) * 10;
        return articleDao.findAllPageByCreateTime(startRow, 10);
    }

    @Override
    public List<Blog> getBlogListByView(Integer page) {
        int startRow = (page - 1) * 10;
        return articleDao.findAllPageByViews(startRow, 10);
    }

    @Override
    public long getAllCountByCategory(String category) {
        return articleDao.countByCategory(category);
    }

    @Override
    public List<Blog> getBlogListByCategory(Integer page, String category) {
        int startRow = (page - 1) * 10;
        return articleDao.findAllPageByCategoryAndCreateTime(category, startRow, 10);
    }

    @Override
    public List<Blog> search(String search, Integer page) {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable = new PageRequest(page, 10, sort);
        List<EsBlog> esBlogs = repository.findDistinctByContentContainingOrTitleContainingOrCategoryContaining(search, search, search, pageable).getContent();
        List<Blog> blogs = new ArrayList<>(50);
        for (EsBlog esBlog : esBlogs){
            Blog blog = new Blog(esBlog);
            blogs.add(blog);
        }
        return blogs;
    }

    @Override
    public List<Blog> getBlogListByUsernameAndPage(Integer page, String username) {
        int startRow = (page - 1) * 10;
        return articleDao.findAllPageByCategoryAndUsername(username, startRow, 10);
    }
}
