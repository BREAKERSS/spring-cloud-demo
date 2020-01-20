package com.wyl.example.feigndemoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动项
 * @author wyl
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.wyl.example"})
public class FeigndemoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeigndemoApiApplication.class, args);
    }

}
