package com.sustly.entry;

import com.sustly.document.EsBlog;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author liyue
 * @date 2019/6/20 14:59
 */
@Entity
public class Blog {
    private Integer id;
    private String content;
    private String createTime;
    private String category;
    private String title;
    private String createUser;
    private Integer views;

    private List<String> images;

    @Transient
    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "create_time")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "create_user")
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Blog(){}
    public Blog(EsBlog esBlog){
        this.category = esBlog.getCategory();
        this.content = esBlog.getContent();
        this.id = esBlog.getBlogId();
        this.title = esBlog.getTitle();
        this.views = esBlog.getViews();
        this.createTime = esBlog.getCreateTime();
        this.createUser= esBlog.getCreateUser();
    }

    @Basic
    @Column(name = "views")
    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return Objects.equals(id, blog.id) &&
                Objects.equals(content, blog.content) &&
                Objects.equals(createTime, blog.createTime) &&
                Objects.equals(category, blog.category) &&
                Objects.equals(title, blog.title) &&
                Objects.equals(createUser, blog.createUser) &&
                Objects.equals(views, blog.views);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, createTime, category, title, createUser, views);
    }
}
