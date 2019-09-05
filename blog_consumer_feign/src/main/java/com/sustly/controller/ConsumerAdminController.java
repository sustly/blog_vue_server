package com.sustly.controller;

import com.sustly.entry.User;
import com.sustly.service.UserClientService;
import com.sustly.dto.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liyue
 * @Date: 19-9-4 下午5:54
 */
@RestController
public class ConsumerAdminController {
    private final UserClientService userService;

    @Autowired
    public ConsumerAdminController(UserClientService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseMsg register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseMsg login(@RequestBody User user){
        return userService.login(user);
    }
}
