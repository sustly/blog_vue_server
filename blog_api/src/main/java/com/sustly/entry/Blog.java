package com.sustly.entry;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private List<String> images;

}
