package com.sustly.controller;

import com.sustly.entry.User;
import com.sustly.service.UserService;
import com.sustly.util.DateUtil;
import com.sustly.util.Md5Util;
import com.sustly.util.ResponseMsg;
import org.assertj.core.util.Lists;
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
    public ResponseMsg<User> register(@RequestBody User user){
        User userFind = userService.findByUsername(user.getUsername());
        if(userFind != null){
            throw new RuntimeException();
        }else {
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(Md5Util.encrypt(user.getPassword()));
            newUser.setCreateTime(DateUtil.getLocalTime());
            userService.save(newUser);
            return ResponseMsg.onOk(newUser,Lists.newArrayList(), true);
        }


    }

    @PostMapping("/login")
    public ResponseMsg<User> login(@RequestBody User user){
        User userFind = userService.findByUsernameAndPassword(user);
        if (userFind == null){
            throw new RuntimeException();
        }
        ResponseMsg<User> userResponseMsg = ResponseMsg.onOk(userFind, Lists.newArrayList(), true);
        return userResponseMsg;
    }
}