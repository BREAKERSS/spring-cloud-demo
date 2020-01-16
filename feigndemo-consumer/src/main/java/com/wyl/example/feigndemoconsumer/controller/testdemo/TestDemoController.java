package com.wyl.example.feigndemoconsumer.controller.testdemo;

import com.wyl.example.feigndemoprovider.service.testdemo.TestDemoService;
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

    @Autowired
    private TestDemoService testDemoService;

    @GetMapping("/get")
    public String findDistrictGrade() throws Exception {
//        throw new Exception("出错啦");
        return testDemoService.get();
    }
}
