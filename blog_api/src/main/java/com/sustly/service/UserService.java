package com.sustly.service;

import com.sustly.entry.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午5:55
 */
@FeignClient(value = "blog-admin-provider-hystrix", fallback=void.class)
public interface UserService {

    @PostMapping("/register")
    User register(@RequestBody User user);
    @PostMapping("/login")
    User login(@RequestBody User user);
}
