package com.wcx.springboot.demo.midware.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZookeeperConnection zookeeperConnection = new ZookeeperConnection();
        ZooKeeper zooKeeper = zookeeperConnection.connect("192.168.64.2:2181", Integer.MAX_VALUE);

        //create data
        String s = zooKeeper.create("/data/name", "wcx".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //exist path
        String path = "/data/name";
        Stat stat = zooKeeper.exists(path, true);

        //get data
        Stat stat1 = new Stat();
        zooKeeper.getData(path, new Watcher() {
            //function of type Watcher. The ZooKeeper ensemble will notify through the Watcher callback when the data of the specified znode changes.
            // This is one-time notification.
            @Override
            public void process(WatchedEvent event) {
                //event path
                String path = event.getPath();
                System.out.println("event path:" + path);
                //event type
                Event.EventType type = event.getType();
                System.out.println("event type:" + type);
                try {
                    byte[] data = zooKeeper.getData(path, null, null);
                    String newData = new String(data, "utf-8");
                    System.out.println("new data:" + newData);
                } catch (KeeperException | InterruptedException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }, stat1);


        //set data
//        Stat stat2 = zooKeeper.setData(path, "hello world".getBytes(), -1);

        //get children
        List<String> children = zooKeeper.getChildren(path, false);

        //delete,删除的节点必须不能有子节点
//        zooKeeper.delete(path, zooKeeper.exists(path, true).getVersion());




        System.out.println("success");
        //await to stop
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();

    }
}
