package com.wcx.springboot.demo.midware.rabbitmq.java;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqChannelFactory {
    public static Channel create() {
        Channel channel=null;
        try {
            Connection connection = MqConnectionFactory.getConnection();
             channel = connection.createChannel();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return channel;
    }
}
