package com.wyl.example.feigndemoprovider.service.testdemo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "feigndemo-api", contextId = "testDemo")
public interface TestDemoService {

    /**
     * 测试获取
     *
     * @return
     */
    @GetMapping("/testDemo/get")
    String get(@RequestParam(value = "param",required = false) String param);

}
