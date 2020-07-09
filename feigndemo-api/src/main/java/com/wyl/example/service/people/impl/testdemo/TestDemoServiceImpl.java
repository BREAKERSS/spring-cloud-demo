package com.wyl.example.service.people.impl.testdemo;

import com.wyl.example.mapper.testdemo.TestDemoMapper;
import com.wyl.example.service.money.SeataTestMoneyService;
import com.wyl.example.service.money.entity.SeataTestMoney;
import com.wyl.example.service.people.SeataTestPeopleService;
import com.wyl.example.service.people.entity.SeataTestPeople;
import com.wyl.example.service.testdemo.TestDemoService;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    private SeataTestMoneyService moneyService;

    @Autowired
    private SeataTestPeopleService peopleService;

//    @Value("${name.username}")
    private String userName;
    private int i = 0;

    @Override
    @GetMapping("/testDemo/get")
    public String get() {
        logger.info("api-b收到请求啦次数：{}", i);
        i++;
        return "userName:" + userName + ",get success";
    }

    @Override
    @PostMapping("/testDemo/get")
    @GlobalTransactional(rollbackFor = Exception.class)
    public String insert() throws Exception {
        SeataTestMoney seataTestMoney = new SeataTestMoney();
        seataTestMoney.setMoney(22.2);
        moneyService.insert(seataTestMoney);
        SeataTestPeople seataTestPeople = new SeataTestPeople();
        seataTestPeople.setName("小月月");
        peopleService.insert(seataTestPeople);

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
