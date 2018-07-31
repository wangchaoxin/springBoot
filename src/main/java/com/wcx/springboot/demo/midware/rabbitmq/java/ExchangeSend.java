package com.wcx.springboot.demo.midware.rabbitmq.java;

import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * 发送到exchange  fanout
 */
public class ExchangeSend implements MqConfig{



    public static void main(String[] args) throws IOException {
        Channel channel = MqChannelFactory.create();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
//        channel.basicPublish("", MqConfig.QUEUE_NAME, null, message.getBytes());

        /*发送到指定exchange,不指定routing_key*/
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());

        System.out.println(" [x] Sent '" + message + "'");
    }
}
