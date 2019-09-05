package com.sustly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午5:41
 */
@SpringBootApplication
@EnableEurekaServer
public class BlogEureka {
    public static void main(String[] args) {
        SpringApplication.run(BlogEureka.class, args);
    }
}
