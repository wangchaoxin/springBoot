package com.wcx.springboot.demo.midware.rabbitmq.java.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wcx.springboot.demo.midware.rabbitmq.java.MqConfig;
import org.apache.commons.lang3.StringUtils;

import static com.wcx.springboot.demo.midware.rabbitmq.java.MqConfig.QUEUE_NAME;

/**
 * 发送到指定某一个queuename,发送到default_exchange,QUEUE_NAME是routing_key
 * 默认是负载均衡的，多个consumer均摊消息
 */
public class Sender {
    /**
     *
     * @param args
     */
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


            //To send, we must declare a queue for us to send to; then we can publish a message to the queue:
            //Declaring a queue is idempotent - it will only be created if it doesn't exist already
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            //发送到默认exchange,默认就是负载均衡的
            for (int i = 0; i < 10; i++) {
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            }
            System.out.println(" [x] Sent '" + message + "'");

            //close the channel and connection
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
