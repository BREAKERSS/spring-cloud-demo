package com.wyl.example.feigndemoconsumer.controller.testdemo;

import com.wyl.example.feigndemoprovider.service.testdemo.TestDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wyl
 */
@RestController
@RequestMapping("/testdemo")
public class TestDemoController {

    @Autowired
    private TestDemoService testDemoService;

    @GetMapping("/get")
    public String findDistrictGrade() {
//        throw new Exception("出错啦");
        try {
            Map<String,String> map = new HashMap<>();
            return testDemoService.get(map.get("12323"));
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
