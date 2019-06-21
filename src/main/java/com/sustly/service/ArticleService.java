package com.sustly.service;

import com.sustly.entry.Blog;

/**
 * ArticleService
 * @author admin
 */
public interface ArticleService {
    /**
     * save
     * @param blog blog
     */
    void save(Blog blog);

    /**
     * findById
     * @param id id
     * @return Blog
     */
    Blog findById(Integer id);

    void delete(Integer id);
}
