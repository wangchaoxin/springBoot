package com.wcx.springboot.demo.midware.rabbitmq.java;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv {
    private static final String QUEUE_NAME = "hello";

    /**
     * 发送到单个queue上
     *
     * @param args
     * @throws IOException
     * @throws TimeoutException
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = MqChannelFactory.create(false);

        if (channel == null)
            return;
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        /*autoAck设置为false,不自动确认，每条消息都需要手动确认，保证消息可靠*/
        channel.basicConsume(QUEUE_NAME, true, consumer);

    }
}
