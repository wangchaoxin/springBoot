package com.wcx.springboot.demo.midware.zookeeper.zk_client;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Client {

    public static final String PATH = "/book";

    public static void main(String[] args) {
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            //connectionTimeout  如果该时间段没有连上，抛出异常
            ZkClient zkClient = new ZkClient("192.168.64.2:2181", 5000);

            //递归创建节点，原生不支持
            zkClient.createPersistent("/test/c1", true);

            //自动完成逐层遍历删除节点的工作，原生有子节点不能删除
            //boolean b = zkClient.deleteRecursive("/test/c1");

            //返回子节点的相对路径  c1  c2
            List<String> children = zkClient.getChildren("/test");

            //监听子节点变化
            //listenChild(zkClient);

            //监听数据变化
            //dataChange(zkClient);

            //getData
            //Object o = zkClient.readData(PATH);

            //writeData
            //zkClient.writeData(PATH,"11");

            //exist
            //zkClient.exists(PATH);


            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听数据变化
     * @param zkClient
     * @throws InterruptedException
     */
    private static void dataChange(ZkClient zkClient) throws InterruptedException {
        if (!zkClient.exists(PATH)) {
            zkClient.createPersistent(PATH);
        }
        zkClient.subscribeDataChanges(PATH, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println(String.format("dataPath [%s],data [%s]", dataPath, data));
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println(String.format("dataPath [%s]", dataPath));
            }
        });
        Thread.sleep(500);
        zkClient.writeData(PATH, true);
        Thread.sleep(500);
        zkClient.delete(PATH);
    }

    private static void listenChild(ZkClient zkClient) throws InterruptedException {
        //监听子节点变化,监听不是一次性的，会一直生效
        if (!zkClient.exists(PATH)) {
            zkClient.createPersistent(PATH);
        }
        zkClient.subscribeChildChanges(PATH, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println(String.format("parent path [%s],currentChilds [%s]", parentPath, currentChilds));
            }
        });
        Thread.sleep(500);
        zkClient.createPersistent(PATH + "/c2", true);
        Thread.sleep(500);
        zkClient.delete(PATH + "/c2");

    }
}
