package com.powernode.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${my.exchangeName}")
    private String exchangeName;

    @Value("${my.queueName}")
    private String queueName;

    //交换机
    @Bean
    public DirectExchange directExchange(){
        return ExchangeBuilder.directExchange(exchangeName).build();
    }

    //队列
    @Bean
    public Queue queue(){
        return QueueBuilder.durable(queueName).build();
    }

    //绑定
    @Bean
    public Binding binding(DirectExchange directExchange,Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with("info");
    }

}
