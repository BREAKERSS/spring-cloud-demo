package com.wyl.example.feigndemoconsumer.controller.testdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wyl
 */
@RestController
@RequestMapping("/config")
public class ConfigController {
    @GetMapping("/get")
    public String findDistrictGrade() throws Exception {
//        throw new Exception("出错啦");
        return "config get";
    }
}
