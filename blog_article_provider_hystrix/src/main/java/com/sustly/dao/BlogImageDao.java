package com.sustly.dao;

import com.sustly.entry.BlogImage;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午5:07
 */
@Mapper
public interface BlogImageDao {

    @Select("select * from blog_image where blog_id=#{blogId}")
    List<BlogImage> findByBlogId(@Param("blogId") Integer blogId);

    @Delete("delete * from blog_image where blog_id=#{blogId}")
    void deleteAllByBlogId(@Param("blogId") Integer blogId);

    @Insert("insert into blog_image(blog_id, image_url) values (#{blogId}, #{imageUrl})")
    void save(BlogImage blogImage);
}
