package com.sustly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午5:48
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClients(value = {
        @RibbonClient(value = "blog-article-provider-hystrix"),
        @RibbonClient(value = "blog-admin-provider-hystrix")
})
@EnableFeignClients(basePackages = {"com.sustly"})
public class BlogConsumerFeign {
    public static void main(String[] args) {
        SpringApplication.run(BlogConsumerFeign.class, args);
    }
}
