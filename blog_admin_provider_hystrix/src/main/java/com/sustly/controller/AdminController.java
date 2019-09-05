package com.sustly.controller;

import com.sustly.entry.User;
import com.sustly.service.UserService;
import com.sustly.util.DateUtil;
import com.sustly.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyue
 * @date 2019/5/29 9:07
 */
@RestController
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        User userFind = userService.findByUsername(user.getUsername());
        if(userFind != null){
            throw new RuntimeException();
        }else {
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(Md5Util.encrypt(user.getPassword()));
            newUser.setCreateTime(DateUtil.getLocalTime());
            userService.save(newUser);
            return newUser;
        }


    }

    @PostMapping("/login")
    public User login(@RequestBody User user){
        User userFind = userService.findByUsernameAndPassword(user);
        if (userFind == null){
            throw new RuntimeException();
        }
        return userFind;
    }
}