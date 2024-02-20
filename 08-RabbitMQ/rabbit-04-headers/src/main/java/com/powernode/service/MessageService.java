package com.powernode.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class MessageService {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Value("${my.exchangeName}")
    private String exchangeName;

    public void sendMsg(){
        //消息属性
        MessageProperties messageProperties = new MessageProperties();
        Map<String, Object> headers = new HashMap<>();
        headers.put("type","s");
        headers.put("status","0");
        //设置消息头
        messageProperties.setHeaders(headers);
        //添加了消息属性
        Message message = MessageBuilder.withBody("hello world".getBytes()).andProperties(messageProperties).build();
        //头部交换机 路由key无所谓 因为不用它 用上面的消息头
        rabbitTemplate.convertAndSend(exchangeName,"",message);
        log.info("消息发送完毕！！！");
    }
}
