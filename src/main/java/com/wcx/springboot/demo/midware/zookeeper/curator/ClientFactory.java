package com.wcx.springboot.demo.midware.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Hashtable;
import java.util.Map;

public class ClientFactory {

    private static Map<String, CuratorFramework> table = new Hashtable<>();

    static CuratorFramework getClient() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.64.2:2181", 5000, 3000, retryPolicy);
        client.start();
        return client;
    }

    static CuratorFramework getClient(String key) {
        if (table.get(key) != null && table.get(key).getState() != CuratorFrameworkState.STOPPED) {
            return table.get(key);
        }
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.64.2:2181", 5000, 3000, retryPolicy);
        client.start();
        table.put(key, client);
        return client;
    }
}
