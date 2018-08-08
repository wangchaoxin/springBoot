package com.wcx.springboot.demo.midware.rabbitmq.java;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.StringUtils;

public class MqConnectionFactory {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(MqConfig.HOST);
        factory.setPort(MqConfig.PORT);
        if (StringUtils.isNoneBlank(MqConfig.USER_NAME) && StringUtils.isNoneBlank(MqConfig.PASS_WORD)) {
            factory.setUsername(MqConfig.USER_NAME);
            factory.setPassword(MqConfig.PASS_WORD);
        }

        try {
            connection = factory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return connection;
        }
        return connection;
    }

}
