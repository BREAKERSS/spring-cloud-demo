package com.wyl.example.listenner;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RabbitListener(queues = "rabbitDelayQueue")
public class DelayReceiver {

    private static final Logger logger = LoggerFactory.getLogger(DelayReceiver.class);


    @RabbitHandler
    public void process(String people, Channel channel, Message message) throws Exception {
        //todo 信息消费处理
        logger.info("people-api 收到 rabbitDelay队列消息,{}", people);
        logger.info("【订单延时时间】" + new Date().toString() +"test"  );
        try {
//            throw new Exception();
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

//            logger.info("receiver success");
        } catch (Exception e) {
            e.printStackTrace();
            //丢弃这条消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            logger.info("receiver fail");
        }
    }
}
