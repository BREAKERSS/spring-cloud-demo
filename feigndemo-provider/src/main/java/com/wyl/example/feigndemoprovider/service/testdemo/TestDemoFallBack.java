package com.wyl.example.feigndemoprovider.service.testdemo;

import org.springframework.stereotype.Component;

@Component
public class TestDemoFallBack implements TestDemoService {
    @Override
    public String get() {
        return "call fallback";
    }
}
