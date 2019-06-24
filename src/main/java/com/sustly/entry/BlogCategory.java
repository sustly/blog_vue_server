package com.sustly.entry;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author liyue
 * @date 2019/6/24 15:03
 */
@Entity
@Table(name = "blog_category", schema = "blog", catalog = "")
public class BlogCategory {
    private Integer id;
    private Integer blogId;
    private Integer categoryId;

    @Id
    @Column(name = "id")
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "blog_id")
    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    @Basic
    @Column(name = "category_id")
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogCategory that = (BlogCategory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(blogId, that.blogId) &&
                Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blogId, categoryId);
    }
}
