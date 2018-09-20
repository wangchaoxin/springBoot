package com.wcx.springboot.demo.java.connection_pool;

import java.util.List;

public interface ConnectionPool {
    Connection getConnection();

    boolean releaseConnection(Connection connection);

    List<Connection> getConnectionPool();

    int getSize();

    String getUrl();

    String getUser();

    String getPassword();

    void shutdown();
}
