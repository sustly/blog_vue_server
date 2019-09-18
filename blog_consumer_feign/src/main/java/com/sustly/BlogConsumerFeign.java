package com.sustly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午5:48
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.sustly"})
@EnableDiscoveryClient
public class BlogConsumerFeign {
    public static void main(String[] args) {
        SpringApplication.run(BlogConsumerFeign.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
