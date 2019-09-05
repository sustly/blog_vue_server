package com.sustly.controller;

import com.sustly.entry.Blog;
import com.sustly.service.ArticleService;
import com.sustly.service.BlogImageService;
import com.sustly.util.BeanUtil;
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
    private final BlogImageService blogImageService;

    @Autowired
    public ArticleController(ArticleService articleService, BlogImageService blogImageService) {
        this.articleService = articleService;
        this.blogImageService = blogImageService;
    }

    @PostMapping("/saveArticle")
    public void save(@RequestBody(required = false) Blog blog) {
        blog.setCreateTime(DateUtil.getLocalTime());
        articleService.save(blog);
        blogImageService.saveAllImage(blog);
    }

    @PostMapping("/getArticle/{id}")
    public Blog get(@PathVariable("id") Integer id) {
        return articleService.findById(id);
    }

    @PostMapping("/deleteArticle/{id}")
    public void delete(@PathVariable("id") Integer id) {
        articleService.delete(id);
        blogImageService.deleteAllByBlogId(id);
    }

    @PostMapping("/getArticleListByTime/{page}")
    public Map<String, Object> getArticleList(@PathVariable("page") Integer page) {
        HashMap<String, Object> map = new HashMap<>(2);
        long records = articleService.getAllCount();
        map.put("records", records);
        List<Blog> blogList = articleService.getBlogListByPage(page);
        map.put("blogList", blogList);
        return map;
    }

    @PostMapping("/getArticleListByTime/{username}/{page}")
    public Map<String, Object> getArticleByUserList(@PathVariable("username") String username,
                                                    @PathVariable("page") Integer page) {
        HashMap<String, Object> map = new HashMap<>(2);
        long records = articleService.getAllCount();
        map.put("records", records);
        List<Blog> blogList = articleService.getBlogListByUsernameAndPage(page, username);
        map.put("blogList", blogList);
        return map;
    }

    @PostMapping("/getArticleListByView/{page}")
    public Map<String, Object> getArticleListByTime(@PathVariable("page") Integer page) {
        HashMap<String, Object> map = new HashMap<>(2);
        long records = articleService.getAllCount();
        map.put("records", records);
        List<Blog> blogList = articleService.getBlogListByView(page);
        map.put("blogList", blogList);
        return map;
    }

    @PostMapping("/getArticleListByCategory/{category}/{page}")
    public Map<String, Object> getArticleListByCategory(@PathVariable("category") String category,
                                                        @PathVariable("page") Integer page) {
        HashMap<String, Object> map = new HashMap<>(2);
        long records = articleService.getAllCountByCategory(category);
        map.put("records", records);
        List<Blog> blogList = articleService.getBlogListByCategory(page, category);
        map.put("blogList", blogList);
        return map;
    }

    @PostMapping("/view/{id}")
    public void view(@PathVariable("id") Integer id) {
        Blog blog = articleService.findById(id);
        Integer views = blog.getViews();
        views = views + 1;
        blog.setViews(views);

        articleService.save(blog);
    }

    @PostMapping("/updateArticle")
    public void updateArticle(@RequestBody(required = false) Blog blog) throws Exception {
        Blog findBlog = articleService.findById(blog.getId());
        Blog updateBean = (Blog) BeanUtil.updateBean(findBlog, blog);
        articleService.save(updateBean);
        blogImageService.saveAllImage(updateBean);
    }

    @PostMapping("/getArticleListBySearch/{search}/{page}")
    public Map<String, Object> getArticleListBySearch(@PathVariable("search") String search,
                                                      @PathVariable("page") Integer page) {


        HashMap<String, Object> map = new HashMap<>(1);
        List<Blog> blogList = articleService.search(search, page);
        map.put("records", blogList.size());
        map.put("blogList", blogList);
        return map;
    }
}