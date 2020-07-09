package com.wyl.example.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wyl
 * @date 2020/3/26
 */
@Component
public class MqSend implements RabbitTemplate.ReturnCallback,RabbitTemplate.ConfirmCallback {
    private static final Logger logger = LoggerFactory.getLogger(MqSend.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Boolean peopleSend(String people) {
        logger.info("开始向people管道推送: {}" , people);
        this.rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData();

        this.rabbitTemplate.convertAndSend("peopleDirectExchange", "peopleDirectRouting", people, correlationData);
        return true;
    }

    public Boolean moneySend(String money) {
        logger.info("开始向money管道推送: {}" , money);
        this.rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData();

        this.rabbitTemplate.convertAndSend("moneyDirectExchange", "moneyDirectRouting", money, correlationData);
        return true;
    }

    public Boolean delaySend(String msg) {
        logger.info("开始向delay管道推送: {}" , msg);
        this.rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate.setConfirmCallback(this);
        logger.info("【订单生成时间】" + new Date().toString() +"【1分钟后检查订单是否已经支付】"  );

        this.rabbitTemplate.convertAndSend("delayDirectExchange","delayDirectRouting", msg, message-> {
            // 如果配置了 params.put("x-message-ttl", 5 * 1000);
            // 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
            message.getMessageProperties().setExpiration(1000 * 60 + "");
            return message;
        });
        return true;
    }


    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("sender return success" + message.toString() + "===" + i + "===" + s1 + "===" + s2);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack) {
            logger.info("HelloSender消息发送失败" + cause + String.valueOf(correlationData));
        } else {
            logger.info("HelloSender 消息发送成功 ");
        }
    }
}
