package com.wcx.springboot.demo.midware.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * 事件监听
 */
public class ListenerExample {

    private static Logger logger = LoggerFactory.getLogger(ListenerExample.class);

    public static final String PATH = "/test/curator/listener";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = ClientFactory.getClient();

        if (client.checkExists().forPath(PATH) == null) {
            String s = client.create().forPath(PATH);
        }
        //数据变化
        //dataChange(client);

        //子节点变化,无法对二级子节点进行事件监听
        //childChange(client);

        //连接状态变化
        connectionChange(client);


        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 连接状态变化
     *
     * @param client
     */
    private static void connectionChange(CuratorFramework client) throws Exception {
        logger.info("connectionChange");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ConnectionStateListener listener = new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                System.out.println(newState);
                if (newState == ConnectionState.CONNECTED) {
                    countDownLatch.countDown();
                }
            }
        };
        client.getConnectionStateListenable().addListener(listener);

        // due to CURATOR-72, this was causing a LOST event to precede the CONNECTED event
        client.create().inBackground().forPath("/foo");

        countDownLatch.await();
        System.out.println("end");
    }

    /**
     * 子节点变化
     *
     * @param client
     * @throws Exception
     */
    private static void childChange(CuratorFramework client) throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, PATH, true);
        pathChildrenCache.start();
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                switch (event.getType()) {
                    case CHILD_ADDED:
                        System.out.println("add data:" + event.getData().getPath());
                        break;
                    case CHILD_REMOVED:
                        System.out.println("remove data:" + event.getData().getPath());
                        break;

                    case CHILD_UPDATED:
                        System.out.println("update data:" + event.getData().getPath());
                    default:
                        break;
                }
            }
        });
        client.create().forPath(PATH + "/c1");
        Thread.sleep(1000);
        client.delete().forPath(PATH + "/c1");
    }

    /**
     * 节点数据变化
     *
     * @param client
     * @throws Exception
     */
    private static void dataChange(CuratorFramework client) throws Exception {
        //监听分为：节点监听，子节点监听
        //监听节点数据变化,可以指定数据是否压缩
        NodeCache nodeCache = new NodeCache(client, PATH, false);
        nodeCache.start();

        //nodecache不仅可以监听内容变更，也能监听节点是否存在，如果不存在，节点创建后触发，如果节点被删除，不触发监听
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("current data :" + new String(nodeCache.getCurrentData().getData(), "utf-8"));
            }
        });
        client.setData().forPath(PATH, "new data".getBytes());
    }
}
