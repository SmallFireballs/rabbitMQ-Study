package com.powernode.message;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
@Slf4j
public class MessageReceive {
    @RabbitListener(queues = {"queue.normal.5"})
    public void receiveMsg(Message message, Channel channel) {
        //获取消息属性
        MessageProperties messageProperties = message.getMessageProperties();
        //获取消息的唯一标识
        long deliveryTag = messageProperties.getDeliveryTag();

        try {
            byte[] body = message.getBody();
            String str = new String(body);
            log.info("接收到的消息为：{}，接收时间为：{}", str, new Date());
            //TODO 业务逻辑处理
            int a = 1 / 0;
            //消费者的手动确认，false 只确认当前消息，true为批量确认
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            log.info("接受者出现问题:{}", e.getMessage());
            try {
                //参数1 消息唯一标识 确定是哪条消息 参数2 是否批量处理 参数3 是否重新入队
                // 不会进入死信队列
//                channel.basicNack(deliveryTag, false, true);
                channel.basicNack(deliveryTag, false, false);// basicNack 和 basicReject区别：在于参数2 是否批量处理 否则只能单条处理
                // 拒绝消息 参数1 消息标识 参数2 是否重新入队
                channel.basicReject(deliveryTag,false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
}
