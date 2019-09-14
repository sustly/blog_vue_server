package com.sustly.service;

import com.sustly.dto.ResponseMsg;
import com.sustly.entry.Blog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午6:10
 */
@FeignClient(value = "BLOG-ARTICLE-PROVIDER-HYSTRIX", fallbackFactory = ArticleServiceClientFallBackFactory.class)
public interface ArticleClientService {
    @PostMapping("/saveArticle")
    void save(@RequestBody(required = false) Blog blog);

    @PostMapping("/getArticle/{id}")
    ResponseMsg get(@PathVariable("id") Integer id);

    @PostMapping("/deleteArticle/{id}")
    void delete(@PathVariable("id") Integer id);

    @PostMapping("/getArticleListByTime/{page}")
    ResponseMsg getArticleList(@PathVariable("page") Integer page);

    @PostMapping("/getArticleListByTime/{username}/{page}")
    ResponseMsg getArticleByUserList(@PathVariable("username") String username,
                                                    @PathVariable("page") Integer page);
    @PostMapping("/getArticleListByView/{page}")
    ResponseMsg getArticleListByTime(@PathVariable("page") Integer page);

    @PostMapping("/getArticleListByCategory/{category}/{page}")
    ResponseMsg getArticleListByCategory(@PathVariable("category") String category,
                                                        @PathVariable("page") Integer page);
    @PostMapping("/view/{id}")
    void view(@PathVariable("id") Integer id);

    @PostMapping("/updateArticle")
    void updateArticle(@RequestBody(required = false) Blog blog)throws Exception;

    @PostMapping("/getArticleListBySearch/{search}/{page}")
    ResponseMsg getArticleListBySearch(@PathVariable("search") String search,
                                                      @PathVariable("page") Integer page);
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    @ResponseBody
    ResponseMsg uploadFile(MultipartFile image);

    @RequestMapping(value = "/getImg", method = RequestMethod.GET)
    void getFile(@RequestParam("url")String url,
                        HttpServletResponse response) throws IOException;
}
