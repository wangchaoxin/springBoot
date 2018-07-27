package com.wcx.springboot.demo.rabbitmq;

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
    }
}
