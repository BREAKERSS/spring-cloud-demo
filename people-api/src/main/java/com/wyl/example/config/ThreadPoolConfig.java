package com.wyl.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    @Value("${thread.core_poll_size}")
    private String corePollSize;

    @Value("${thread.max_pool_size}")
    private String maxPoolSize;

    @Value("${thread.queue_capacity}")
    private String queueCapacity;

    @Value("${thread.keep_alive_seconds}")
    private String keepAliveSeconds;

    @Value("${thread.thread_name_prefix}")
    private String threadNamePrefix;

    @Bean("getThreadPoolExecutor")
    public ThreadPoolTaskExecutor getThreadPoolExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(Integer.parseInt(corePollSize));
        threadPoolTaskExecutor.setMaxPoolSize(Integer.parseInt(maxPoolSize));
        threadPoolTaskExecutor.setQueueCapacity(Integer.parseInt(queueCapacity));
        threadPoolTaskExecutor.setKeepAliveSeconds(Integer.parseInt(keepAliveSeconds));
        threadPoolTaskExecutor.setThreadNamePrefix(threadNamePrefix);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.setAwaitTerminationSeconds(60);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

}
