package com.wcx.springboot.demo.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String queueName, String message) {
        logger.info("Sender.send,queueName:" + queueName + ",message:" + message);
        this.rabbitTemplate.convertAndSend(queueName, message);
    }

}