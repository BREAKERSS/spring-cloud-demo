package com.wyl.example.controller;

import com.wyl.example.service.money.SeataTestMoneyService;
import com.wyl.example.service.money.entity.SeataTestMoney;
import com.wyl.example.service.people.SeataTestPeopleService;
import com.wyl.example.service.people.entity.SeataTestPeople;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seata")
public class SeataController {
    private static final Logger logger = LoggerFactory.getLogger(SeataController.class);

    @Autowired
    private SeataTestMoneyService moneyService;
    @Autowired
    private SeataTestPeopleService peopleService;

    @PostMapping("/seataTest")
    public String seataTest() throws Exception {
        SeataTestMoney money = new SeataTestMoney();
        money.setId("1");
        money.setMoney(22.22);
        moneyService.insert(money);
        SeataTestPeople people = new SeataTestPeople();
        people.setId("1");
        people.setName("wyl");
        peopleService.insert(people);
        return "config get";
    }
}
