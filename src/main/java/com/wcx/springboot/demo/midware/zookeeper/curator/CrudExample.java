package com.wcx.springboot.demo.midware.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CrudExample {

    public static final String PATH = "/test/curator";

    public static void main(String[] args) throws Exception {
        //重试策略
        //ExponentialBackoffRetry 重试的时间越来越长,初始重启3s,重试3次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 3);
        //connectString集群由字符串分割,192.168.64.2:2181,192.168.64.3:2181
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.64.2:2181", 5000, 3000, retryPolicy);
        //调用start完成创建
        client.start();

        //创建节点
        //createNode(client);

        //删除节点
        //deleteNode(client);

        //读取节点
        //readNode(client);

        //更新数据
        updateNode(client);

        Thread.sleep(Integer.MAX_VALUE);
    }




    private static void createNode(CuratorFramework client) throws Exception {
        //创建一个节点，初始值为空，如果没设置属性，则是持久节点
        String s = client.create().forPath(PATH);

        //创建一个节点，有初始内容
        String s1 = client.create().forPath(PATH + "/c1", "init".getBytes());

        //创建一个临时节点，初始内容为空
        client.create().withMode(CreateMode.EPHEMERAL).forPath(PATH + "/c2");

        //创建一个临时节点，自动递归创建父节点
        //zookeeper中规定所有的非叶子节点都必须为持久节点
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(PATH + "/c3");
    }

    private static void deleteNode(CuratorFramework client) throws Exception {
        //删除一个节点
        client.delete().forPath(PATH + "/c1");

        //删除一个节点，并递归删除其所有子节点
        client.delete().deletingChildrenIfNeeded().forPath(PATH + "/c2");

        //删除一个节点，强制保证删除,知道客户端会话有效，会在后台持续删除,知道删除成功
        //类似master选举这种场景必须要求删除成功
        client.delete().guaranteed().forPath(PATH + "/c3");
    }

    private static void readNode(CuratorFramework client) throws Exception {
        //读取一个节点内容
        String res = new String(client.getData().forPath(PATH + "/c1"), "utf-8");

        //读取一个节点内容,同时获取该节点的stat,传入stat变量
        Stat stat = new Stat();
        String res1 = new String(client.getData().storingStatIn(stat).forPath(PATH + "/c1"), "utf-8");
    }
    private static void updateNode(CuratorFramework client) throws Exception {
        //更新一个数据的内容
        Stat statRes = client.setData().withVersion(2).forPath(PATH + "/c1", "new value".getBytes());

        //更新一个数据的内容,强制指定版本更新,withVersion接口用来实现CAS，version的版本信息通常是由一个旧的stat对象中获取
        Stat stat = new Stat();
        String res1 = new String(client.getData().storingStatIn(stat).forPath(PATH + "/c1"), "utf-8");

        Stat statRes1 = client.setData().withVersion(stat.getVersion()).forPath(PATH + "/c1", "new value".getBytes());

      

    }
}
