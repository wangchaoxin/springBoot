package com.wcx.springboot.demo.midware.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 绑定rabbit操作类
 */
@Component
public class Sender {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String queueName, String message) {
        logger.info("Sender.send,queueName:" + queueName + ",message:" + message);
        this.rabbitTemplate.convertAndSend(queueName, message);
    }


    public void convertAndSend(String queueName, Object object) {
        logger.info("Sender.send,queueName:" + queueName + ",message:" + object.toString());
        this.rabbitTemplate.convertAndSend(queueName, object);
    }
    /*spring boot 定时器*/
    /*@Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(){
        rabbitTemplate.convertAndSend("user",new User(5,"hahaha"));
    }*/
}