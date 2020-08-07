package com.wyl.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitDelayConfig {
    //队列名称
    final static String queue = "rabbitDelayQueue";

    //交换机名称
    final static String exchangeName = "rabbitDelayExchange";

    // routingKey
    final static String routingKey = "rabbitDelayRouting";


    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Bean
    public Queue rabbitDelayQueue() {
        // 第一个参数是创建的queue的名字，第二个参数是是否支持持久化
        return new Queue(RabbitDelayConfig.queue, true);
    }

    @Bean
    public CustomExchange rabbitDelayExchange() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(RabbitDelayConfig.exchangeName, "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding bindingRabbitDelay() {
        return BindingBuilder.bind(rabbitDelayQueue()).to(rabbitDelayExchange()).with(RabbitDelayConfig.routingKey).noargs();
    }


/**      @Bean public RabbitTemplate rabbitTemplate(){
//若使用confirm-callback ，必须要配置publisherConfirms 为true
connectionFactory.setPublisherConfirms(true);
//若使用return-callback，必须要配置publisherReturns为true
connectionFactory.setPublisherReturns(true);
RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true
// rabbitTemplate.setMandatory(true);

// 如果消息没有到exchange,则confirm回调,ack=false; 如果消息到达exchange,则confirm回调,ack=true
rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
@Override public void confirm(CorrelationData correlationData, boolean ack, String cause) {
if(ack){
log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
}else{
log.info("消息发送失败:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
}
}
});

//如果exchange到queue成功,则不回调return;如果exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
@Override public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
}
});
return rabbitTemplate;
}
 **/
}
