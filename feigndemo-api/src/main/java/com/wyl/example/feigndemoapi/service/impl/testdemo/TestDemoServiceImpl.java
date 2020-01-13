package com.wyl.example.feigndemoapi.service.impl.testdemo;

import com.wyl.example.feigndemoapi.mapper.testdemo.TestDemoMapper;
import com.wyl.example.feigndemoprovider.service.testdemo.TestDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class TestDemoServiceImpl implements TestDemoService {
    @Autowired
    private TestDemoMapper testDemoMapper;

    @Override
    @GetMapping("/testDemo/get")
//    @Transactional
    public String get() {
        return "get success";
    }
}
