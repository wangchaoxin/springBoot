package com.wcx.springboot.demo.midware.rabbitmq.java;

public interface MqConfig {
    String host = "10.10.237.105";
    int port = 5672;

    String QUEUE_NAME = "hello";
    String EXCHANGE_NAME = "logs";
}
