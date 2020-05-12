package com.wyl.example.controller;

import com.wyl.example.service.testdemo.TestDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wyl
 */
@RestController
@RequestMapping("/testdemo")
public class TestDemoController {
    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);
    @Autowired
    private TestDemoService testDemoService;
    private int i = 0;

    @GetMapping("/get")
    public String findDistrictGrade() {
//        throw new Exception("出错啦");
        try {
            logger.info("从网关那收到请求啦次数：{}", i);
            i++;
            return testDemoService.get();
        } catch (Exception e) {
            logger.error("出错啦", e);
            return "error";
        }
    }
}
