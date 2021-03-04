package com.wyl.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.wyl.example.mapper"})
@EnableFeignClients(basePackages = {"com.wyl.example.service"})
@EnableAsync
public class PeopleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeopleApiApplication.class, args);
    }

}
