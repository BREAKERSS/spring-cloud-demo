package com.wyl.example.configdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Value("${server.port:1234}")
    private Integer port;

    @RequestMapping("/getPort")
    public Integer getPort() {
        return port;
    }


}
