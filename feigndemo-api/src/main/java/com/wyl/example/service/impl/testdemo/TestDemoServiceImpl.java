package com.wyl.example.service.impl.testdemo;

import com.wyl.example.mapper.testdemo.TestDemoMapper;
import com.wyl.example.service.testdemo.TestDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wyl
 */
@RefreshScope
@RestController
public class TestDemoServiceImpl implements TestDemoService {
    private static final Logger logger = LoggerFactory.getLogger(TestDemoServiceImpl.class);
    @Autowired
    private TestDemoMapper testDemoMapper;

    @Value("${name.username}")
    private String userName;
    private int i = 0;

    @Override
    @GetMapping("/testDemo/get")
    public String get() {
        logger.info("api-b收到请求啦次数：{}", i);
        i++;
        return "userName:" + userName + ",get success";
    }

    /**
     * 离职小技巧
     *
     * @param args
     */
    public static void main(String[] args) {
        String a = "123";
        // \u000d a = "333";
        System.out.println(a);
    }
}
