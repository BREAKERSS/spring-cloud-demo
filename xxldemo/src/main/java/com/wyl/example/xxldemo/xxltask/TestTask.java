package com.wyl.example.xxldemo.xxltask;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

@Component
public class TestTask {

    @XxlJob("/testJob")
    public ReturnT<String> testJob(String param) throws Exception {
        XxlJobLogger.log("testJob");
        return ReturnT.SUCCESS;
    }
}
