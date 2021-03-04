package com.wyl.example.controller;

import com.wyl.example.sender.MqSend;
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
    @Autowired
    private MqSend mqSend;
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

    @GetMapping("/peopleSend")
    public String peopleSend() {
        try {
            for (int j = 50; j > 0; j--) {
                mqSend.moneySend(String.valueOf(j));
                Thread.sleep(1000);
            }
            return String.valueOf(true);
        } catch (Exception e) {
            logger.error("出错啦", e);
            return "error";
        }
    }

    @GetMapping("/moneySend")
    public String moneySend(String money) {
        try {
            String msg = String.valueOf(i++);
            return String.valueOf(mqSend.moneySend(msg));
        } catch (Exception e) {
            logger.error("出错啦", e);
            return "error";
        }
    }

    @GetMapping("/delaySend")
    public String delaySend(String msg) {
        try {

            return String.valueOf(mqSend.delaySend(msg));
        } catch (Exception e) {
            logger.error("出错啦", e);
            return "error";
        }
    }

    @GetMapping("/rabbitDelaySend")
    public String rabbitDelaySend(String msg) {
        try {

            return String.valueOf(mqSend.rabbitDelaySend(msg));
        } catch (Exception e) {
            logger.error("出错啦", e);
            return "error";
        }
    }


}
