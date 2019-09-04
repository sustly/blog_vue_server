package com.sustly.service;

import com.sustly.entry.Blog;
import org.springframework.cloud.netflix.feign.FeignClient;
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
import java.util.Map;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午6:10
 */
@FeignClient(value = "blog-article-provider-hystrix", fallbackFactory = ArticleServiceClientFallBackFactory.class)
public interface ArticleClientService {
    @PostMapping("/saveArticle")
    void save(@RequestBody(required = false) Blog blog);

    @PostMapping("/getArticle/{id}")
    Blog get(@PathVariable("id") Integer id);

    @PostMapping("/deleteArticle/{id}")
    void delete(@PathVariable("id") Integer id);

    @PostMapping("/getArticleListByTime/{page}")
    Map<String, Object> getArticleList(@PathVariable("page") Integer page);

    @PostMapping("/getArticleListByTime/{username}/{page}")
    Map<String, Object> getArticleByUserList(@PathVariable("username") String username,
                                                    @PathVariable("page") Integer page);
    @PostMapping("/getArticleListByView/{page}")
    Map<String, Object> getArticleListByTime(@PathVariable("page") Integer page);

    @PostMapping("/getArticleListByCategory/{category}/{page}")
    Map<String, Object> getArticleListByCategory(@PathVariable("category") String category,
                                                        @PathVariable("page") Integer page);
    @PostMapping("/view/{id}")
    void view(@PathVariable("id") Integer id);

    @PostMapping("/updateArticle")
    void updateArticle(@RequestBody(required = false) Blog blog)throws Exception;

    @PostMapping("/getArticleListBySearch/{search}/{page}")
    Map<String, Object> getArticleListBySearch(@PathVariable("search") String search,
                                                      @PathVariable("page") Integer page);
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> uploadFile(MultipartFile image);

    @RequestMapping(value = "/getImg", method = RequestMethod.GET)
    void getFile(@RequestParam("url")String url,
                        HttpServletResponse response) throws IOException;
}
