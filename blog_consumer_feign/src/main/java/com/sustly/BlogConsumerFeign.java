package com.sustly;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午5:48
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.sustly"})
@EnableDiscoveryClient
public class BlogConsumerFeign {
    public static void main(String[] args) {
        SpringApplication.run(BlogConsumerFeign.class, args);
    }

    @Bean
    @SentinelRestTemplate
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
