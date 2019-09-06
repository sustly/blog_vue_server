package com.sustly.service;

import com.sustly.dto.ResponseMsg;
import com.sustly.entry.Blog;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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
            public ResponseMsg get(Integer id) {
                return ResponseMsg.onFail("博客找不到了！");
            }

            @Override
            public void delete(Integer id) {

            }

            @Override
            public ResponseMsg getArticleList(Integer page) {
                return ResponseMsg.onFail("没有页数了！！");
            }

            @Override
            public ResponseMsg getArticleByUserList(String username, Integer page) {
                return ResponseMsg.onFail("没有页数了！");
            }

            @Override
            public ResponseMsg getArticleListByTime(Integer page) {
                return ResponseMsg.onFail("没有页数了！");
            }

            @Override
            public ResponseMsg getArticleListByCategory(String category, Integer page) {
                return ResponseMsg.onFail("没有页数了！");
            }

            @Override
            public void view(Integer id) {

            }

            @Override
            public void updateArticle(Blog blog) {

            }

            @Override
            public ResponseMsg getArticleListBySearch(String search, Integer page) {
                return ResponseMsg.onFail("找不到与"+search+"有关的数据！");
            }

            @Override
            public ResponseMsg
            uploadFile(MultipartFile image) {
                return ResponseMsg.onFail("上传失败！");
            }

            @Override
            public void getFile(String url, HttpServletResponse response) {

            }
        };
    }
}
