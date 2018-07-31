package com.wcx.springboot.demo.midware.rabbitmq.java;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqConnectionFactory {
    private static Connection connection;

    public static Connection getConnection() throws IOException, TimeoutException {
        if (connection != null)
            return connection;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(MqConfig.host);
        factory.setPort(MqConfig.port);
        connection = factory.newConnection();
        return connection;
    }

}
