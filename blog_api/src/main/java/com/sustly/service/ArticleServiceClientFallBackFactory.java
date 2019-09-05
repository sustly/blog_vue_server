package com.sustly.service;

import com.google.common.collect.Maps;
import com.sustly.entry.Blog;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午6:29
 */
@Component
public class ArticleServiceClientFallBackFactory implements FallbackFactory<ArticleClientService> {
    @Override
    public ArticleClientService create(Throwable throwable) {
        return new ArticleClientService() {
            @Override
            public void save(Blog blog) {

            }

            @Override
            public Blog get(Integer id) {
                return new Blog(0, null, null, null, null, null, null, null);
            }

            @Override
            public void delete(Integer id) {

            }

            @Override
            public Map<String, Object> getArticleList(Integer page) {
                return Maps.newHashMap();
            }

            @Override
            public Map<String, Object> getArticleByUserList(String username, Integer page) {
                return Maps.newHashMap();
            }

            @Override
            public Map<String, Object> getArticleListByTime(Integer page) {
                return Maps.newHashMap();
            }

            @Override
            public Map<String, Object> getArticleListByCategory(String category, Integer page) {
                return Maps.newHashMap();
            }

            @Override
            public void view(Integer id) {

            }

            @Override
            public void updateArticle(Blog blog) {

            }

            @Override
            public Map<String, Object> getArticleListBySearch(String search, Integer page) {
                return Maps.newHashMap();
            }

            @Override
            public Map<String, Object> uploadFile(MultipartFile image) {
                return Maps.newHashMap();
            }

            @Override
            public void getFile(String url, HttpServletResponse response) {

            }
        };
    }
}
