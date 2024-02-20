package com.powernode.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConfigurationProperties(prefix = "my")//配置属性 从application.yml文件中
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

    //rabbitmq三部曲
    // 1.定义交换机
    @Bean
    public DirectExchange directExchange(){
        return ExchangeBuilder.directExchange(exchangeName).build();
    }
    // 2.定义队列
    @Bean
    public Queue queueA(){
        return QueueBuilder.durable(queueAName).build();
    }
    @Bean
    public Queue queueB(){
        return QueueBuilder.durable(queueBName).build();
    }
    // 3.绑定交换机和队列
    @Bean
    public Binding bindingA(DirectExchange directExchange, Queue queueA){
        //将队列A绑定到扇形交换机 使用建造者模式
        return BindingBuilder.bind(queueA).to(directExchange).with("error");
    }
    @Bean
    public Binding bindingB1(DirectExchange directExchange, Queue queueB){
        //将队列B绑定到扇形交换机 使用建造者模式
        return BindingBuilder.bind(queueB).to(directExchange).with("info");
    }
    @Bean
    public Binding bindingB2(DirectExchange directExchange, Queue queueB){
        //将队列B绑定到扇形交换机 使用建造者模式
        return BindingBuilder.bind(queueB).to(directExchange).with("error");
    }
    @Bean
    public Binding bindingB3(DirectExchange directExchange, Queue queueB){
        //将队列B绑定到扇形交换机 使用建造者模式
        return BindingBuilder.bind(queueB).to(directExchange).with("warning");
    }
}
