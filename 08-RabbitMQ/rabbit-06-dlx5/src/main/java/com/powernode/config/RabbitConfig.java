package com.powernode.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {
    @Value("${my.exchangeNormalName}")
    private String exchangeNormalName;

    @Value("${my.queueNormalName}")
    private String queueNormalName;

    @Value("${my.exchangeDlxName}")
    private String exchangeDlxName;

    @Value("${my.queueDlxName}")
    private String queueDlxName;

    //直连交换机
    @Bean
    public DirectExchange normalExchange() {
        return ExchangeBuilder.directExchange(exchangeNormalName).build();
    }

    //正常队列
    @Bean
    public Queue normalQueue() {
        Map<String, Object> arguments = new HashMap<>();
//        arguments.put("x-message-ttl",15000); //设置队列的过期时间
        // 死信交换机重点：这两个参数
        arguments.put("x-dead-letter-exchange", exchangeDlxName); // 设置队列的死信交换机
        arguments.put("x-dead-letter-routing-key", "error");//设置死信路由key，要和死信交换机和死信队列绑定的key一样,因为死信交换机是直连交换机
        return QueueBuilder.durable(queueNormalName)
                .withArguments(arguments) // 设置队列的参数
                .build();
    }

    //直连交换机与正常队列绑定
    //注意形参要写 交换机 和 队列 的方法名字
    @Bean
    public Binding bindingNormal(DirectExchange normalExchange, Queue normalQueue) {
        return BindingBuilder.bind(normalQueue).to(normalExchange).with("order");
    }

    /**
     * 死信交换机 其实也是一个普通的交换机
     *
     * @return
     */
    @Bean
    public DirectExchange dlxExchange() {
        return ExchangeBuilder.directExchange(exchangeDlxName).build();
    }

    //死信队列
    @Bean
    public Queue dlxQueue() {
        return QueueBuilder.durable(queueDlxName).build();
    }

    //绑定 私信交换机 和 死信队列
    @Bean
    public Binding bindingDlx(DirectExchange dlxExchange, Queue dlxQueue) {
        return BindingBuilder.bind(dlxQueue).to(dlxExchange).with("error");
    }
}
