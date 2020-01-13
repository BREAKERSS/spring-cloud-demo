package com.wyl.example.feigndemoapi.service.impl.testdemo;

import com.wyl.example.feigndemoprovider.service.testdemo.TestDemoService;
import org.springframework.stereotype.Service;

@Service
public class TestDemoFallBack implements TestDemoService {
    @Override
    public String get() {
        return "call fallback";
    }
}
