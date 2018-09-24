package com.wcx.springboot.demo.midware.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

public class ClientFactory2 {



    public static final int DEFAULT_SESSION_TIMEOUT_MS = 5000;
    public static final int DEFAULT_CONNECTION_TIMEOUT_MS = 5000;

    /**
     * Create a new client
     *
     * @param connectString       list of servers to connect to
     * @param sessionTimeoutMs    session timeout
     * @param connectionTimeoutMs connection timeout
     * @param retryPolicy         retry policy to use
     * @return client
     */
    public static CuratorFramework newClient(String connectString, int sessionTimeoutMs, int connectionTimeoutMs, RetryPolicy retryPolicy) {
        return CuratorFrameworkFactory.builder().
                connectString(connectString).
                sessionTimeoutMs(sessionTimeoutMs).
                connectionTimeoutMs(connectionTimeoutMs).
                retryPolicy(retryPolicy).
                build();
    }

    public static CuratorFramework newClient(String connectString, RetryPolicy retryPolicy) {
        return newClient(connectString, DEFAULT_SESSION_TIMEOUT_MS, DEFAULT_CONNECTION_TIMEOUT_MS, retryPolicy);
    }

    public static void main(String[] args) {
        CuratorFramework client = CuratorFrameworkFactory.newClient("", 1000, 1000, new RetryNTimes(0, 0));

    }

}
