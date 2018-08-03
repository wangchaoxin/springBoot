package com.wcx.springboot.demo.midware.rabbitmq.amqp;

import com.wcx.springboot.demo.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mq")
public class MqController {

    @Autowired
    private Sender sender;

    @RequestMapping("send")
    public void send(){
        sender.send("hello","hello world");
        /*发送对象*/
        sender.convertAndSend("user",new User(2,"ChaoxinWang"));
    }
}
