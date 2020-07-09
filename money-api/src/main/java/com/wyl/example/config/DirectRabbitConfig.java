package com.wyl.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DirectRabbitConfig {
    //队列
    @Bean
    public Queue moneyDirectQueue() {
        return new Queue("moneyDirectQueue", true);  //true 是否持久
    }

    @Bean
    public Queue peopleDirectQueue() {
        return new Queue("peopleDirectQueue", true);  //true 是否持久
    }

    //Direct交换机
    @Bean
    DirectExchange moneyDirectExchange() {
        return new DirectExchange("moneyDirectExchange");
    }

    @Bean
    DirectExchange peopleDirectExchange() {
        return new DirectExchange("peopleDirectExchange");
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键
    @Bean
    Binding bindingAlipayDirect() {
        return BindingBuilder.bind(moneyDirectQueue()).to(moneyDirectExchange()).with("moneyDirectRouting");
    }

    @Bean
    Binding bindingUnionpayDirect() {
        return BindingBuilder.bind(peopleDirectQueue()).to(peopleDirectExchange()).with("peopleDirectRouting");
    }

    //死信队列 交换机标识符
    public static final String DEAD_LETTER_QUEUE_KEY = "x-dead-letter-exchange";

    //死信队列交换机绑定键标识符
    public static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    //todo 死信队列实现延时队列
    @Bean
    public Queue delayDirectQueue() {
        // 将普通队列绑定到死信队列交换机上
        Map<String, Object> args = new HashMap<>(2);
        //args.put("x-message-ttl", 5 * 1000);//直接设置 Queue 延迟时间 但如果直接给队列设置过期时间,这种做法不是很灵活
        //这里采用发送消息动态设置延迟时间,这样我们可以灵活控制
        args.put(DEAD_LETTER_QUEUE_KEY, "deathDirectExchange");
        args.put(DEAD_LETTER_ROUTING_KEY, "deathDirectRouting");
        return new Queue("delayDirectQueue", true, false, false, args);
    }

    //Direct交换机
    @Bean
    DirectExchange delayDirectExchange() {
        return new DirectExchange("delayDirectExchange");
    }

    @Bean
    Binding bindingDelayDirect() {
        return BindingBuilder.bind(delayDirectQueue()).to(delayDirectExchange()).with("delayDirectRouting");
    }

    //声明死信队列
    @Bean
    public Queue deathDeathQueue() {
        return new Queue("deathDirectQueue", true);
    }

    //死信队列交换机
    @Bean
    DirectExchange deathDirectExchange() {
        return new DirectExchange("deathDirectExchange");
    }

    //死信队列绑定
    @Bean
    Binding bindingDeathDirect() {
        return BindingBuilder.bind(deathDeathQueue()).to(deathDirectExchange()).with("deathDirectRouting");
    }
}
