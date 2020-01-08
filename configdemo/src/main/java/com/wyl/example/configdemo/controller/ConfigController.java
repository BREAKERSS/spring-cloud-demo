package com.wyl.example.configdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {
    @Value("${user.age:22}")
    private String age;

    @RequestMapping("/get")
    public String get() {
        return age;
    }
}
