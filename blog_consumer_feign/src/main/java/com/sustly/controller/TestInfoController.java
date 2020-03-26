package com.sustly.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyue
 * @date 20-3-26 下午3:10
 */
@RestController
@RefreshScope
public class TestInfoController {

    @Value("${config.info}")
    private String info;

    @GetMapping("/getInfo")
    public String getInfo(){
        return info;
    }
}
