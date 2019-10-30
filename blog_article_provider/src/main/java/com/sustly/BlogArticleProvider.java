package com.sustly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午5:35
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.systly.dao")
public class BlogArticleProvider {
    public static void main(String[] args) {
        SpringApplication.run(BlogArticleProvider.class, args);
    }
}
