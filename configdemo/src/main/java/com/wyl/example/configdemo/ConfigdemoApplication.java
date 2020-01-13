package com.wyl.example.configdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConfigdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigdemoApplication.class, args);
    }

}
