package com.wcx.springboot.demo.midware.rabbitmq.java;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * exchange fanout reveive,pub/sub
 */
public class ExchangeRecv implements MqConfig {
    public static void main(String[] args) throws IOException {
        Channel channel = MqChannelFactory.create(false);
        if (channel == null)
            return;
        /*声明exchange，将queue绑定到exchange*/
//        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
//        String queueName = channel.queueDeclare().getQueue();
        String queueName="add.device";
        channel.queueBind(queueName, EXCHANGE_NAME, "");

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
        /*从生产的quename进行消费*/
        channel.basicConsume(queueName, true, consumer);
    }
}
