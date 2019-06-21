package com.sustly.dao;

import com.sustly.entry.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author admin
 */
public interface ArticleDao extends JpaRepository<Blog, Integer> {
    /**
     * findById
     * @param id id
     * @return Blog
     */
    Blog findById(Integer id);

    /**
     *deleteById
     * @param id id
     */
    void deleteById(Integer id);
}
