package com.wyl.example.listenner;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class MoneyPull {
    private static final Logger logger = LoggerFactory.getLogger(MoneyPull.class);
    @Autowired
    @Qualifier("getThreadPoolExecutor")
    private ThreadPoolTaskExecutor poolTaskExecutor;
    @Value("${spring.rabbitmq.host}")
    private String mqHost;
    @Value("${spring.rabbitmq.port}")
    private int mqPort;
    @Value("${spring.rabbitmq.username}")
    private String mqUsername;
    @Value("${spring.rabbitmq.password}")
    private String mqPassword;


    /**
     * 队列名称
     */
    private final static String QUEUE_NAME = "hello";

    /**
     * 创建对像
     */
    ConnectionFactory factory;
    /**
     * 联接信息
     */
    Connection connection;
    /**
     * 频道
     */
    Channel channel = null;


    @PostConstruct
    public void process() throws Exception {
        Thread t = new Thread(() -> {
            //创建连接工厂
            factory = new ConnectionFactory();
            factory.setHost(mqHost);
            factory.setPort(mqPort);
            factory.setUsername(mqUsername);
            factory.setPassword(mqPassword);
            factory.setAutomaticRecoveryEnabled(true);
            factory.setTopologyRecoveryEnabled(false);
            try {
                //创建连接
                connection = factory.newConnection();
                //创建消息信道
                channel = connection.createChannel();
            } catch (IOException e) {
                logger.error("IOException", e);
            } catch (TimeoutException e) {
                logger.error("TimeoutException", e);
            }
            while (true) {
                try {
                    //当线程中 活跃线程数小于最大线程数时 拉取消息
                    if (poolTaskExecutor.getActiveCount() < poolTaskExecutor.getMaxPoolSize()) {
                        logger.info("开始获取消息");
                        GetResponse response = channel.basicGet("moneyDirectQueue", false);
                        if (response == null) {
                            logger.info("队列中没有消息进入等待");
                            Thread.sleep(10000);
                            continue;
                        }

                        poolTaskExecutor.execute(() -> {
                            try {
                                logger.info("people-api 收到 money队列消息,{}", new String(response.getBody()));
                                Thread.sleep(1000);
                                channel.basicAck(response.getEnvelope().getDeliveryTag(), false);
                            } catch (InterruptedException | IOException e) {
                                e.printStackTrace();
                            }
                        });
                        //            processThread(money);

                    } else {
                        logger.info("活跃线程已满！");
                        Thread.sleep(10000);
                    }
                } catch (Exception e) {
                    logger.error("订单生成失败", e);
                }
            }
        });
        t.start();
    }
}
