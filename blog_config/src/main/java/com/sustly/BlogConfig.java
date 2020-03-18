package com.sustly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author liyue
 * @date 20-3-18 上午11:02
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class BlogConfig {
    public static void main(String[] args) {
        SpringApplication.run(BlogConfig.class, args);
    }
}
