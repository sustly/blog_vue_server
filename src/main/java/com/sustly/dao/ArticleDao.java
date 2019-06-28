package com.sustly.dao;

import com.sustly.entry.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author admin
 */
public interface ArticleDao extends JpaRepository<Blog, Integer>, JpaSpecificationExecutor<Blog> {
    /**
     * findById
     * @param id id
     * @return Blog
     */
    Blog getBlogById(Integer id);

/*    *//**
     *deleteById
     * @param id id
     */
    void deleteById(Integer id);

    /**
     * countByCategory
     * @param category category
     * @return long
     */
    long countByCategory(String category);
}
