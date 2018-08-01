package com.wcx.springboot.demo.midware.rabbitmq.java;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

/**
 * 负载均衡发送消息,消息持久化，消息确认
 */
public class NewSend {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        Channel channel = MqChannelFactory.create(false);

        /*设置消息持久化*/
        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

        String message = getMessage(argv);

        /*Now we need to mark our messages as persistent -
        by setting MessageProperties (which implements BasicProperties)
        to the value PERSISTENT_TEXT_PLAIN.*/
        channel.basicPublish("", TASK_QUEUE_NAME,
                MessageProperties.PERSISTENT_TEXT_PLAIN,
                message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");
    }

    private static String getMessage(String[] strings) {
        if (strings.length < 1)
            return "Hello World!";
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
