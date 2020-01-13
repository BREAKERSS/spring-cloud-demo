package com.wyl.example.feigndemoprovider.service.testdemo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "feigndemo-api", contextId = "testDemo", fallback = TestDemoFallBack.class)
public interface TestDemoService {

    /**
     * 测试获取
     *
     * @return
     */
    @GetMapping("/testDemo/get")
    String get();

}
