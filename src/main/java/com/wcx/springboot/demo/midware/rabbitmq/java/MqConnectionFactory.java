package com.wcx.springboot.demo.midware.rabbitmq.java;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MqConnectionFactory {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(MqConfig.host);
        factory.setPort(MqConfig.port);
        try {
            connection = factory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return connection;
        }
        return connection;
    }

}
