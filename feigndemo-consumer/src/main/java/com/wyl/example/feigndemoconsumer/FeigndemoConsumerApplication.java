package com.wyl.example.feigndemoconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wyl
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.wyl.example"})
//@MapperScan(basePackages = {"com.jiayiedu.mapper"})
@ComponentScan(basePackages = {"com.wyl.example"})
public class FeigndemoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeigndemoConsumerApplication.class, args);
    }


}
