package com.powernode.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Slf4j
public class MessageService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMsg() {
        //使用建造者模式创建消息
        Message message = MessageBuilder.withBody("hello world".getBytes()).build();
        //参数1：交换机 参数2：路由key 参数3：消息
        rabbitTemplate.convertAndSend("exchange.topic", "info", message);
        log.info("消息发送完毕,发送时间为：{}", new Date());
    }
}
