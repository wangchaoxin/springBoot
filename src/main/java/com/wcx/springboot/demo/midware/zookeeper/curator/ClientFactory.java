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
//        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.64.2:2181", 5000, 3000, retryPolicy);


        //sessionTimout时间内没有心跳检测，会话失效
        //connectionTimeout连接超时，如果连接超过此时间，抛出异常进行重试
        CuratorFramework client = CuratorFrameworkFactory.newClient("zookeeper", 1000, 3000, retryPolicy);
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

    static CuratorFramework getClient(String ip, int port) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(String.format("%s:%s", ip, port), 5000, 3000, retryPolicy);
        client.start();
        return client;
    }
}
