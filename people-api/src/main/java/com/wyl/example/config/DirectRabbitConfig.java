package com.wyl.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {
    //队列
    @Bean
    public Queue moneyDirectQueue() {
        return new Queue("moneyDirectQueue",true);  //true 是否持久
    }
    @Bean
    public Queue peopleDirectQueue() {
        return new Queue("peopleDirectQueue",true);  //true 是否持久
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
}
