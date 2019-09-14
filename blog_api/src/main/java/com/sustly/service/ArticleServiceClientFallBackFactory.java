package com.sustly.service;

import com.sustly.dto.ResponseMsg;
import com.sustly.entry.Blog;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午6:29
 */
@Component
@Slf4j
public class ArticleServiceClientFallBackFactory implements FallbackFactory<ArticleClientService> {
    @Override
    public ArticleClientService create(Throwable throwable) {
        return new ArticleClientService() {
            @Override
            public void save(Blog blog) {
                log.info(throwable.getMessage());
            }

            @Override
            public ResponseMsg get(Integer id) {
                log.info(throwable.getMessage());
                return ResponseMsg.onFail("服务器正在升级，请稍后访问！");
            }

            @Override
            public void delete(Integer id) {
                log.info(throwable.getMessage());
            }

            @Override
            public ResponseMsg getArticleList(Integer page) {
                log.info(throwable.getMessage());
                return ResponseMsg.onFail("服务器正在升级，请稍后访问！");
            }

            @Override
            public ResponseMsg getArticleByUserList(String username, Integer page) {
                log.info(throwable.getMessage());
                return ResponseMsg.onFail("服务器正在升级，请稍后访问！");
            }

            @Override
            public ResponseMsg getArticleListByTime(Integer page) {
                log.info(throwable.getMessage());
                return ResponseMsg.onFail("服务器正在升级，请稍后访问！");
            }

            @Override
            public ResponseMsg getArticleListByCategory(String category, Integer page) {
                log.info(throwable.getMessage());
                return ResponseMsg.onFail("服务器正在升级，请稍后访问！");
            }

            @Override
            public void view(Integer id) {
                log.info(throwable.getMessage());
            }

            @Override
            public void updateArticle(Blog blog) {
                log.info(throwable.getMessage());
            }

            @Override
            public ResponseMsg getArticleListBySearch(String search, Integer page) {
                log.info(throwable.getMessage());
                return ResponseMsg.onFail("服务器正在升级，请稍后访问！");
            }

            @Override
            public ResponseMsg
            uploadFile(MultipartFile image) {
                log.info(throwable.getMessage());
                return ResponseMsg.onFail("服务器正在升级，请稍后访问！");
            }

            @Override
            public void getFile(String url, HttpServletResponse response) {
                log.info(throwable.getMessage());
            }
        };
    }
}
