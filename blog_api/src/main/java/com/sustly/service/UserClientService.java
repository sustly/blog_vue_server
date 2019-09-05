package com.sustly.service;

import com.sustly.entry.User;
import com.sustly.util.ResponseMsg;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午5:55
 */
@FeignClient(value = "BLOG-ADMIN-PROVIDER-HYSTRIX", fallbackFactory=UserServiceClientFallBackFactory.class)
public interface UserClientService {

    @PostMapping("/register")
    ResponseMsg<User> register(@RequestBody User user);
    @PostMapping("/login")
    ResponseMsg<User> login(@RequestBody User user);
}
