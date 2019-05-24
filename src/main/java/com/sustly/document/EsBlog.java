package com.sustly.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

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
    private String content;
    private String createTime;
    private String summary;
    private String title;
    private String tags;

    protected EsBlog() {
    }

    public EsBlog(Integer id, String content, String createTime, String summary, String title, String tags) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
        this.summary = summary;
        this.title = title;
        this.tags = tags;
    }
}
