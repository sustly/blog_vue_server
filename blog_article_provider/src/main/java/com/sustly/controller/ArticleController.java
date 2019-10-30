package com.sustly.controller;

import com.sustly.dto.Pagination;
import com.sustly.dto.ResponseMsg;
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

import java.util.List;

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
    public ResponseMsg get(@PathVariable("id") Integer id) {
        Blog blog = articleService.findById(id);
        return ResponseMsg.onOk(blog);
    }

    @PostMapping("/deleteArticle/{id}")
    public void delete(@PathVariable("id") Integer id) {
        articleService.delete(id);
        blogImageService.deleteAllByBlogId(id);
    }

    @PostMapping("/getArticleListByTime/{page}")
    public ResponseMsg getArticleList(@PathVariable("page") Integer page) {
        Long records = articleService.getAllCount();
        List<Blog> blogList = articleService.getBlogListByPage(page);
        return ResponseMsg.onOk(new Pagination(records.intValue(), blogList));
    }

    @PostMapping("/getArticleListByTime/{username}/{page}")
    public ResponseMsg getArticleByUserList(@PathVariable("username") String username,
                                                    @PathVariable("page") Integer page) {
        Long records = articleService.getAllCount();
        List<Blog> blogList = articleService.getBlogListByUsernameAndPage(page, username);
        return ResponseMsg.onOk(new Pagination(records.intValue(), blogList));
    }

    @PostMapping("/getArticleListByView/{page}")
    public ResponseMsg getArticleListByTime(@PathVariable("page") Integer page) {
        Long records = articleService.getAllCount();
        List<Blog> blogList = articleService.getBlogListByView(page);
        return ResponseMsg.onOk(new Pagination(records.intValue(), blogList));
    }

    @PostMapping("/getArticleListByCategory/{category}/{page}")
    public ResponseMsg getArticleListByCategory(@PathVariable("category") String category,
                                                        @PathVariable("page") Integer page) {
        Long records = articleService.getAllCountByCategory(category);
        List<Blog> blogList = articleService.getBlogListByCategory(page, category);

        return ResponseMsg.onOk(new Pagination(records.intValue(), blogList));
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
    public ResponseMsg getArticleListBySearch(@PathVariable("search") String search,
                                                      @PathVariable("page") Integer page) {

        List<Blog> blogList = articleService.search(search, page);
        return ResponseMsg.onOk(new Pagination(blogList.size(), blogList));
    }
}