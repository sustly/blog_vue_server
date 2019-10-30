package com.sustly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.systly.dao")
public class BlogAdminProvider {
    public static void main(String[] args) {
        SpringApplication.run(BlogAdminProvider.class, args);
    }
}