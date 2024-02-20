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
    //交换机的名字
    private String exchangeName;

    @Value("${my.queueAName}")
    //队列A的名字
    private String queueAName;

    @Value("${my.queueBName}")
    //队列B的名字
    private String queueBName;

    @Bean
    public HeadersExchange headersExchange(){
        return ExchangeBuilder.headersExchange(exchangeName).build();
    }

    @Bean
    public Queue queueA(){
        return QueueBuilder.durable(queueAName).build();
    }
    @Bean
    public Queue queueB(){
        return QueueBuilder.durable(queueBName).build();
    }
    @Bean
    public Binding bindingA(HeadersExchange headersExchange,Queue queueA){
        Map<String,Object> headerValues = new HashMap<>();
        headerValues.put("type","m");
        headerValues.put("status","1");
        return BindingBuilder.bind(queueA).to(headersExchange).whereAll(headerValues).match();
    }
    @Bean
    public Binding bindingB(HeadersExchange headersExchange,Queue queueB){
        Map<String,Object> headerValues = new HashMap<>();
        headerValues.put("type","s");
        headerValues.put("status","0");
        return BindingBuilder.bind(queueB).to(headersExchange).whereAll(headerValues).match();
    }
}
