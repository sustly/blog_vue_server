package com.sustly.controller;

import com.sustly.dto.ResponseMsg;
import com.sustly.entry.Blog;
import com.sustly.service.ArticleClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午6:09
 */
@RestController
public class ConsumerArticleController {

    private final ArticleClientService articleService;

    @Autowired
    public ConsumerArticleController(ArticleClientService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/saveArticle")
    public void save(@RequestBody(required = false) Blog blog){
        articleService.save(blog);
    }

    @PostMapping("/getArticle/{id}")
    public ResponseMsg get(@PathVariable("id") Integer id){
        return articleService.get(id);
    }

    @PostMapping("/deleteArticle/{id}")
    public void delete(@PathVariable("id") Integer id){
        articleService.delete(id);
    }
    @PostMapping("/getArticleListByTime/{page}")
    public ResponseMsg getArticleList(@PathVariable("page") Integer page){
        return articleService.getArticleList(page);
    }
    @PostMapping("/getArticleListByTime/{username}/{page}")
    public ResponseMsg getArticleByUserList(@PathVariable("username") String username,
                                             @PathVariable("page") Integer page){
        return articleService.getArticleByUserList(username,page);
    }
    @PostMapping("/getArticleListByView/{page}")
    public ResponseMsg getArticleListByTime(@PathVariable("page") Integer page){
        return articleService.getArticleListByTime(page);
    }

    @PostMapping("/getArticleListByCategory/{category}/{page}")
    public ResponseMsg getArticleListByCategory(@PathVariable("category") String category,
                                                 @PathVariable("page") Integer page){
        return articleService.getArticleListByCategory(category,page);
    }
    @PostMapping("/view/{id}")
    public void view(@PathVariable("id") Integer id){
        articleService.view(id);
    }

    @PostMapping("/updateArticle")
    public void updateArticle(@RequestBody(required = false) Blog blog)throws Exception{
        articleService.updateArticle(blog);
    }

    @PostMapping("/getArticleListBySearch/{search}/{page}")
    public ResponseMsg getArticleListBySearch(@PathVariable("search") String search,
                                               @PathVariable("page") Integer page){
        return articleService.getArticleListBySearch(search, page);
    }
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMsg uploadFile(MultipartFile image){
        return articleService.uploadFile(image);
    }

    @RequestMapping(value = "/getImg", method = RequestMethod.GET)
    public void getFile(@RequestParam("url")String url,
                 HttpServletResponse response) throws IOException{
        articleService.getFile(url, response);
    }
}
