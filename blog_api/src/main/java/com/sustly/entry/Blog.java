package com.sustly.entry;

import com.sustly.document.EsBlog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author liyue
 * @date 2019/6/20 14:59
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Blog {
    private Integer id;
    private String content;
    private String createTime;
    private String category;
    private String title;
    private String createUser;
    private Integer views;

    private List<String> images;
    public Blog(EsBlog esBlog){
        this.category = esBlog.getCategory();
        this.content = esBlog.getContent();
        this.id = esBlog.getBlogId();
        this.title = esBlog.getTitle();
        this.views = esBlog.getViews();
        this.createTime = esBlog.getCreateTime();
        this.createUser= esBlog.getCreateUser();
    }

}
