package com.wcx.springboot.demo.midware.rabbitmq.java;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class MqChannelFactory {
    public static Channel create(boolean closeConnection) {
        Channel channel = null;
        Connection connection = null;
        connection = MqConnectionFactory.getConnection();
        try {
            if (connection != null) {
                channel = connection.createChannel();
            }
        } catch (IOException e) {
            return null;
        }
        return channel;
    }

    private static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
