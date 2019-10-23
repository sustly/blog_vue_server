package com.sustly.service;

import com.sustly.entry.Blog;

import java.util.List;

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

    /**
     * delete
     * @param id id
     */
    void delete(Integer id);

    /**
     * getAllCount
     * @return long
     */
    long getAllCount();

    /**
     * 分页查询
     * @param page page
     * @return List<Blog>
     */
    List<Blog> getBlogListByPage(Integer page);

    /**
     * 分页查询
     * @param page page
     * @return List<Blog>
     */
    List<Blog> getBlogListByView(Integer page);

    /**
     * getAllCountByCategory
     * @param category category
     * @return long
     */
    long getAllCountByCategory(String category);

    /**
     *  getBlogListByCategory
     * @param page page
     * @param category category
     * @return List<Blog>
     */
    List<Blog> getBlogListByCategory(Integer page, String category);

    /**
     * search
     * @param search search
     * @param page page
     * @return List<Blog>
     */
    List<Blog> search(String search, Integer page);

    /**
     * getBlogListByUsernameAndPage
     * @param page page
     * @param username username
     * @return List<Blog>
     */
    List<Blog> getBlogListByUsernameAndPage(Integer page, String username);
}
