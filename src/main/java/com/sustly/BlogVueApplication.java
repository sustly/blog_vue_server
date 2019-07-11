package com.sustly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author admin
 */
@SpringBootApplication
public class BlogVueApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BlogVueApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BlogVueApplication.class);
    }
}
