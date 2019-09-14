package com.sustly.service;

import com.sustly.dto.ResponseMsg;
import com.sustly.entry.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午5:55
 */
@FeignClient(value = "BLOG-ADMIN-PROVIDER-HYSTRIX", fallbackFactory=UserServiceClientFallBackFactory.class)
public interface UserClientService {

    @PostMapping("/register")
    ResponseMsg register(@RequestBody User user);
    @PostMapping("/login")
    ResponseMsg login(@RequestBody User user);
}
