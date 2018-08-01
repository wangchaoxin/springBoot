package com.wcx.springboot.demo.midware.rabbitmq.java;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;

public class NewRecv {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        Channel channel = MqChannelFactory.create(false);

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        /*fair dispatch，只有consumer确认之后，producer才继续往consumer发送消息
        * 保证负载均衡
        * */
        channel.basicQos(1);
        /*final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                System.out.println(" [x] Received '" + message + "'");
                try {
                    doWork(message);
                } finally {
                    System.out.println(" [x] Done");
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };*/
        final Consumer consumer = new NewConsumer(channel);
        /*不自动提交，每次都需要手动确认*/
        channel.basicConsume(TASK_QUEUE_NAME, false, consumer);
    }

    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}