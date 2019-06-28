package com.sustly.document;

import com.sustly.entry.Blog;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;

import java.io.Serializable;

/**
 * @author liyue
 * @date 2019/5/17 15:41
 */


@Data
@Document(indexName="blog", type="blog")
public class EsBlog implements Serializable {
    @Id
    private Integer id;
    @Field(index = FieldIndex.not_analyzed)
    private Integer blogId;
    private String content;
    @Field(index = FieldIndex.not_analyzed)
    private String createTime;
    private String category;
    private String title;
    @Field(index = FieldIndex.not_analyzed)
    private String createUser;
    @Field(index = FieldIndex.not_analyzed)
    private Integer views;

    protected EsBlog() {
    }

    public EsBlog(Integer id, String content, String createTime, String category, String title, String createUser, Integer views) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
        this.category = category;
        this.title = title;
        this.createUser = createUser;
        this.views = views;
    }

    public EsBlog(Blog blog){
        this.blogId = blog.getId();
        this.category = blog.getCategory();
        this.content = blog.getContent();
        this.createTime = blog.getCreateTime();
        this.createUser = blog.getCreateUser();
        this.title = blog.getTitle();
        this.views = blog.getViews();
    }

    public void update(Blog blog){
        this.blogId = blog.getId();
        this.category = blog.getCategory();
        this.content = blog.getContent();
        this.createTime = blog.getCreateTime();
        this.createUser = blog.getCreateUser();
        this.title = blog.getTitle();
        this.views = blog.getViews();
    }

}
