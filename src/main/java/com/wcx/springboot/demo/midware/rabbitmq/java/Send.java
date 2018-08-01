package com.wcx.springboot.demo.midware.rabbitmq.java;

import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * 默认是轮询发送，两个consumer连接一个queue,平均消费消息
 */
public class Send implements MqConfig{


    /**
     * 发送到default_exchange,QUEUE_NAME是routing_key
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Channel channel = MqChannelFactory.create(false);
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
//        channel.basicPublish("", MqConfig.QUEUE_NAME, null, message.getBytes());

        /*发送到指定exchange*/
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        System.out.println(" [x] Sent '" + message + "'");
    }
}
