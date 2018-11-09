package com.wcx.springboot.demo.midware.rabbitmq.java.simple;

import com.rabbitmq.client.*;
import com.wcx.springboot.demo.midware.rabbitmq.java.MqConfig;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import static com.wcx.springboot.demo.midware.rabbitmq.java.MqConfig.QUEUE_NAME;
/**
 * 发送到指定某一个queuename
 */
public class Reveiver {
    public static void main(String[] args) {
        Connection connection=null;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(MqConfig.HOST);
        factory.setPort(MqConfig.PORT);
        if (StringUtils.isNoneBlank(MqConfig.USER_NAME) && StringUtils.isNoneBlank(MqConfig.PASS_WORD)) {
            factory.setUsername(MqConfig.USER_NAME);
            factory.setPassword(MqConfig.PASS_WORD);
        }
        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();

            //Note that we declare the queue here, as well. Because we might start the consumer before the publisher,
            // we want to make sure the queue exists before we try to consume messages from it
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            channel.basicQos(1); // accept only one unack-ed message at a time (see below)

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                    //如果设置成手动确认，确认该消息
                    //channel.basicAck(envelope.getDeliveryTag(), false);
                }
            };
            //autoAck设置为false,不自动确认，每条消息都需要手动确认，保证消息可靠
            channel.basicConsume(QUEUE_NAME, true, consumer);

            //close connection
            channel.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
