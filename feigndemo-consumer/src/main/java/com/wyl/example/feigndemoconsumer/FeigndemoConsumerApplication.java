package com.wyl.example.feigndemoconsumer;

import com.wyl.example.feigndemoprovider.service.testdemo.TestDemoFallBack;
import com.wyl.example.feigndemoprovider.service.testdemo.TestDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.wyl.example"})
//@MapperScan(basePackages = {"com.jiayiedu.mapper"})
@ComponentScan(basePackages = {"com.wyl.example"})
public class FeigndemoConsumerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FeigndemoConsumerApplication.class, args);
    }


    @Autowired
    TestDemoService testDemoService;
    @Autowired
    TestDemoFallBack testDemoFallBack;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(testDemoService.get());
        System.out.println(testDemoFallBack.get());
    }
}
