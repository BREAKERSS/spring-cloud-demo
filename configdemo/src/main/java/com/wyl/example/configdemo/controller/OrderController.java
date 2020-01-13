package com.wyl.example.configdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wyl
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @RequestMapping("/get")
    public Integer getPort() {
        return 123;
    }
}
