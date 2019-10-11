package com.sustly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午5:35
 */
@SpringBootApplication
@EnableHystrix
@EnableDiscoveryClient
@MapperScan(basePackages = "com.systly.dao")
public class BlogArticleProviderHystrix {
    public static void main(String[] args) {
        SpringApplication.run(BlogArticleProviderHystrix.class, args);
    }
}
