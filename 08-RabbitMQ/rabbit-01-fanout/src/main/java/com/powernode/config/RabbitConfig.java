package com.powernode.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    //rabbitmq三部曲
    // 1.定义交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("exchange.fanout");
    }
    // 2.定义队列
    @Bean
    public Queue queueA(){
        return new Queue("queue.fanout.a");
    }
    @Bean
    public Queue queueB(){
        return new Queue("queue.fanout.b");
    }
    // 3.绑定交换机和队列
    @Bean
    public Binding bindingA(FanoutExchange fanoutExchange, Queue queueA){
        //将队列A绑定到扇形交换机
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }
    @Bean
    public Binding bindingB(FanoutExchange fanoutExchange, Queue queueB){
        //将队列B绑定到扇形交换机
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }
}
