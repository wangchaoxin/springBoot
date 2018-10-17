package com.wcx.springboot.demo.midware.zookeeper.origin;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperConnection {

    // declare zookeeper instance to access ZooKeeper ensemble
    private ZooKeeper zoo;
    final CountDownLatch connectedSignal = new CountDownLatch(1);

    // Method to connect zookeeper ensemble.

    /**
     *
     * @param connectionString
     * @param timeout   会话的超时时间，如果在timeout时间内没有有效的心跳检测，会话失效
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ZooKeeper connect(String connectionString,int timeout) throws IOException, InterruptedException {

        //创建会话
        zoo = new ZooKeeper(connectionString, timeout, new Watcher() {

            public void process(WatchedEvent we) {

                if (we.getState() == Event.KeeperState.SyncConnected) {
                    connectedSignal.countDown();
                }
            }
        });

        connectedSignal.await();
        return zoo;
    }

    // Method to disconnect from zookeeper server
    public void close() throws InterruptedException {
        zoo.close();
    }
}
