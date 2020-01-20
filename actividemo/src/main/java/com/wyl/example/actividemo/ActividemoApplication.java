package com.wyl.example.actividemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class,scanBasePackages="com.wyl.example")
public class ActividemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActividemoApplication.class, args);
    }

}
