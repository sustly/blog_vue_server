package com.sustly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author: liyue
 * @Date: 19-9-5 上午9:28
 */
@SpringBootApplication
@EnableZuulProxy
public class BlogZuul {
        public static void main(String[] args) {
            SpringApplication.run(BlogZuul.class, args);
        }
}
