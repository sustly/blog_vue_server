package com.sustly.dao;

import com.sustly.entry.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午4:24
 */
@Mapper
public interface ArticleDao {

    @Select("select * from blog where id =#{id}")
    Blog getBlogById(@Param("id") Integer id);

    @Insert("insert into blog (content, create_time, category, title, create_user, views) values (#{content},#{createTime},#{category},#{title},#{createUser},#{views} )")
    void save(Blog blog);

    @Delete("delete * from blog where id=#{id}")
    void deleteById(@Param("id") Integer id);

    @Select("select count(*) from blog")
    long count();

    @Select("select * from blog order by create_time desc limit #{startRow}, #{pageSize}")
    List<Blog> findAllPageByCreateTime(@Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize);

    @Select("select * from blog order by views desc limit #{startRow}, #{pageSize}")
    List<Blog> findAllPageByViews(@Param("startRow") Integer startRow, @Param("pageSize")Integer pageSize);

    @Select("select count(*) from blog where category=#{category}")
    long countByCategory(@Param("category") String category);

    @Select("select * from blog where category=#{category} order by create_time desc limit #{startRow}, #{pageSize}")
    List<Blog> findAllPageByCategoryAndCreateTime(@Param("category") String category, @Param("startRow") Integer startRow, @Param("pageSize")int pageSize);

    @Select("select * from blog where create_user=#{username} order by create_time desc limit #{startRow}, #{pageSize}")
    List<Blog> findAllPageByCategoryAndUsername(@Param("username") String username, @Param("startRow") Integer startRow, @Param("pageSize") int pageSize);
}
