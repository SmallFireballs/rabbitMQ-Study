package com.powernode.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {
    @Value("${my.exchangeName}")
    private String exchangeName;

    @Value("${my.queueName}")
    private String queueName;

    //交换机
    @Bean
    public DirectExchange directExchange() {
        return ExchangeBuilder.directExchange(exchangeName).build();
    }

    //队列
    @Bean
    public Queue queue() {
        //方式1 new Queue的方式
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl", 15000);
        return new Queue(queueName, true, false, false, arguments);
        //方式 建造者
//        return QueueBuilder
//                .durable(queueName)
//                .withArguments(arguments)
//                .build();
    }

    //绑定
    @Bean
    public Binding binding(DirectExchange directExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with("info");
    }

}
