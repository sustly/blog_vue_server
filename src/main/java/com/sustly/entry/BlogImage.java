package com.sustly.entry;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author liyue
 * @date 2019/7/5 16:27
 */
@Entity
@Table(name = "blog_image", schema = "blog", catalog = "")
public class BlogImage {
    private Integer id;
    private Integer blogId;
    private String imageUrl;

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
    @Column(name = "blog_Id")
    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    @Basic
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogImage blogImage = (BlogImage) o;
        return Objects.equals(id, blogImage.id) &&
                Objects.equals(blogId, blogImage.blogId) &&
                Objects.equals(imageUrl, blogImage.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blogId, imageUrl);
    }
}
