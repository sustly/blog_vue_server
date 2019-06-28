package com.sustly.controller;

import com.sustly.entry.Blog;
import com.sustly.service.ArticleService;
import com.sustly.util.BeanUtil;
import com.sustly.util.DateUtil;
import org.springframework.beans.BeanUtils;
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

    @PostMapping("/getArticleListByTime/{page}")
    public Map<String, Object> getArticleList(@PathVariable("page") Integer page){
        HashMap<String, Object> map = new HashMap<>(2);
        long records = articleService.getAllCount();
        map.put("records", records);
        List<Blog> blogList = articleService.getBlogListByPage(page);
        map.put("blogList", blogList);
        return map;
    }

    @PostMapping("/getArticleListByView/{page}")
    public Map<String, Object> getArticleListByTime(@PathVariable("page") Integer page){
        HashMap<String, Object> map = new HashMap<>(2);
        long records = articleService.getAllCount();
        map.put("records", records);
        List<Blog> blogList = articleService.getBlogListByView(page);
        map.put("blogList", blogList);
        return map;
    }
    @PostMapping("/getArticleListByCategory/{category}/{page}")
    public Map<String, Object> getArticleListByCategory(@PathVariable("category") String category,
                                                        @PathVariable("page") Integer page){
        HashMap<String, Object> map = new HashMap<>(2);
        long records = articleService.getAllCountByCategory(category);
        map.put("records", records);
        List<Blog> blogList = articleService.getBlogListByCategory(page, category);
        map.put("blogList", blogList);
        return map;
    }

    @PostMapping("/view/{id}")
    public void view(@PathVariable("id") Integer id){
        Blog blog = articleService.findById(id);
        Integer views = blog.getViews();
        views = views + 1;
        blog.setViews(views);

        articleService.save(blog);
    }

    @PostMapping("/updateArticle")
    public Map<String, Object> updateArticle(@RequestBody(required = false) Blog blog) throws Exception {
        HashMap<String, Object> map = new HashMap<>(1);
        Blog findBlog = articleService.findById(blog.getId());
        BeanUtil.updateBean(findBlog, blog);
        articleService.save(findBlog);
        map.put("result", true);
        return map;
    }
}