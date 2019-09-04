package com.sustly.dao;

import com.sustly.entry.Blog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午4:24
 */
@Mapper
public interface ArticleDao {

    @Select("select * from blog where id =#{id}")
    Blog getBlogById(Integer id);

    @Insert("insert into blog (content, create_time, category, title, create_user, views) values (#{content},#{createTime},#{category},#{title},#{createUser},#{views} )")
    void save(Blog blog);

    @Delete("delete * from blog where id=#{id}")
    void deleteById(Integer id);

    @Select("select count(*) from blog")
    long count();

    @Select("select * from blog order by create_time desc limit #{startRow}, #{pageSize}")
    List<Blog> findAllPageByCreateTime(Integer startRow, Integer pageSize);

    @Select("select * from blog order by views desc limit #{startRow}, #{pageSize}")
    List<Blog> findAllPageByViews(Integer startRow, Integer pageSize);

    @Select("select count(*) from blog where category=#{category}")
    long countByCategory(String category);

    @Select("select * from blog where category=#{category} order by create_time desc limit #{startRow}, #{pageSize}")
    List<Blog> findAllPageByCategoryAndCreateTime(String category, int startRow, int pageSize);

    @Select("select * from blog where create_user=#{username} order by create_time desc limit #{startRow}, #{pageSize}")
    List<Blog> findAllPageByCategoryAndUsername(String username, int startRow, int pageSize);
}
