package com.wcx.springboot.demo.midware.rabbitmq.java;

public interface MqConfig {
    int PORT = 5672;

    String HOST = "192.168.200.75";
    String USER_NAME="vmsmquser";
    String PASS_WORD="vmsmqpassword";


    String QUEUE_NAME = "hello";
    String EXCHANGE_NAME = "vms-devsvc";
}
