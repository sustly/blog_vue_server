package com.sustly.controller;

import com.sustly.entry.Blog;
import com.sustly.service.ArticleService;
import com.sustly.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liyue
 * @date 2019/6/20 15:39
 */
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/saveArticle")
    public void save(@RequestBody(required = false) Blog blog){
        blog.setCreateTime(DateUtil.getLocalTime());
        articleService.save(blog);
    }

    @PostMapping("/getArticle/{id}")
    public Map<String, Object> get(@PathVariable("id") Integer id){
        HashMap<String, Object> map = new HashMap<>(1);
        Blog blog = articleService.findById(id);
        if (blog != null) {
            map.put("blog", blog);
            map.put("result", true);
        }else {
            map.put("result", false);
        }
        return map;
    }
    @PostMapping("/deleteArticle/{id}")
    public Map<String, Object> delete(@PathVariable("id") Integer id){
        HashMap<String, Object> map = new HashMap<>(1);
        articleService.delete(id);
        map.put("result", true);
        return map;
    }

    @PostMapping("/getArticleList/{page}")
    public Map<String, Object> getArticleList(@PathVariable("page") Integer page){
        HashMap<String, Object> map = new HashMap<>(2);
        long records = articleService.getAllCount();
        map.put("records", records);
        List<Blog> blogList = articleService.getBlogListByPage(page);
        map.put("blogList", blogList);
        return map;
    }
}