package com.sustly.controller;

import com.sustly.entry.User;
import com.sustly.service.UserService;
import com.sustly.util.DateUtil;
import com.sustly.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
    public Map<String, Object> register(@RequestBody User user){
        Map<String, Object> map = new HashMap<>(3);

            User userFind = userService.findByUsername(user.getUsername());
            if(userFind != null){
                map.put("result",false);
                map.put("message","用户已存在！");
            }else {
                User newUser = new User();
                newUser.setUsername(user.getUsername());
                newUser.setPassword(Md5Util.encrypt(user.getPassword()));
                newUser.setCreateTime(DateUtil.getLocalTime());
                userService.save(newUser);
                map.put("result",true);
                map.put("message","用户创建成功！");
            }

        return map;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user){
        Map<String, Object> map = new HashMap<>(3);
        User userFind = userService.findByUsernameAndPassword(user);
        if (userFind == null){
            map.put("result", false);
            map.put("message", "用户名或密码错误！");
        }else {
            userFind.setLastLoginTime(DateUtil.getLocalTime());
            userService.save(userFind);
            map.put("result", true);
            map.put("user",userFind);
            map.put("message", "登陆成功！");
        }
        return map;
    }
}
