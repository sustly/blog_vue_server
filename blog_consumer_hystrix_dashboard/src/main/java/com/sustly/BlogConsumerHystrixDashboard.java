package com.sustly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Author: liyue
 * @Date: 19-9-5 上午9:44
 */
@SpringBootApplication
@EnableHystrixDashboard
public class BlogConsumerHystrixDashboard {
    public static void main(String[] args) {
        SpringApplication.run(BlogConsumerHystrixDashboard.class, args);
    }
}
