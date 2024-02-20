package com.powernode.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class MessageService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMsg() {
        //定义要发送的消息
        String msg = "hello world";
        Message message = new Message(msg.getBytes());
        rabbitTemplate.convertAndSend("exchange.fanout", "", message);
        log.info("消息发送完毕,发送时间为：{}", new Date());
    }
}
