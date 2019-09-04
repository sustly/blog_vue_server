package com.sustly.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author liyue
 * @date 2019/7/5 16:27
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class BlogImage {
    private Integer id;
    private Integer blogId;
    private String imageUrl;

}
