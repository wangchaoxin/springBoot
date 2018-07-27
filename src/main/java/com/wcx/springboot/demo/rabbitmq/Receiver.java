package com.wcx.springboot.demo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")  //监听某一个queue
public class Receiver {

    @RabbitHandler
    public void process(String message) {
        System.out.println("Receiver : " + message);
    }

}