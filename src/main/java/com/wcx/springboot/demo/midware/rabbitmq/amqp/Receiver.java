/*
package com.wcx.springboot.demo.midware.rabbitmq.amqp;

import com.wcx.springboot.demo.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
  //监听某一个queue
public class Receiver {

    @RabbitListener(queues = "hello")
    @RabbitHandler
    public void process(String message) {
        System.out.println("Receiver : user:" + message);
    }

    */
/**
     * 发送的数据为对象时，接收时参数也为对象
     *//*

    @RabbitListener(queues = "user")
    @RabbitHandler
    public void process(User user) {
        System.out.println("Receiver : hello:" + user.toString());
    }

}
*/
