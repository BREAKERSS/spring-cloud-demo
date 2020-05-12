package com.wyl.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wyl
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.wyl.example"})
//@MapperScan(basePackages = {"com.jiayiedu.mapper"})
//@EnableCircuitBreaker
public class FeigndemoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeigndemoConsumerApplication.class, args);
    }


}
