package com.powernode.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageService {
    @Resource
    private AmqpTemplate amqpTemplate;

    public void sendMsg() {
        Message message = MessageBuilder.withBody("hello world".getBytes()).build();
        //参数1：交换机 参数2：路由key 参数3：消息
        amqpTemplate.convertAndSend("exchange.fanout", "hello.world", message);
    }
}
