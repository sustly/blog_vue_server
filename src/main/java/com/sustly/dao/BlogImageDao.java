package com.sustly.dao;

import com.sustly.entry.BlogImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author liyue
 * @date 2019/7/5 16:27
 */
public interface BlogImageDao extends JpaRepository<BlogImage, Integer>{

    /**
     * deleteAllByBlogId
     * @param blogId blogId
     */
    void deleteAllByBlogId(Integer blogId);

    /**
     * findByBlogId
     * @param blogId blogId
     * @return List<BlogImage>
     */
    List<BlogImage> findByBlogId(Integer blogId);

}
