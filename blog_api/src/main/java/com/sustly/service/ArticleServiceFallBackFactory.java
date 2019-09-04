package com.sustly.service;

import com.sustly.entry.Blog;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午6:29
 */
@Component
public class ArticleServiceFallBackFactory implements FallbackFactory<ArticleService> {
    @Override
    public ArticleService create(Throwable throwable) {
        return new ArticleService() {
            @Override
            public void save(Blog blog) {

            }

            @Override
            public Blog get(Integer id) {
                return null;
            }

            @Override
            public void delete(Integer id) {

            }

            @Override
            public Map<String, Object> getArticleList(Integer page) {
                return null;
            }

            @Override
            public Map<String, Object> getArticleByUserList(String username, Integer page) {
                return null;
            }

            @Override
            public Map<String, Object> getArticleListByTime(Integer page) {
                return null;
            }

            @Override
            public Map<String, Object> getArticleListByCategory(String category, Integer page) {
                return null;
            }

            @Override
            public void view(Integer id) {

            }

            @Override
            public void updateArticle(Blog blog) throws Exception {

            }

            @Override
            public Map<String, Object> getArticleListBySearch(String search, Integer page) {
                return null;
            }

            @Override
            public Map<String, Object> uploadFile(MultipartFile image) {
                return null;
            }

            @Override
            public void getFile(String url, HttpServletResponse response) throws IOException {

            }
        };
    }
}
