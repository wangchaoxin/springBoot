package com.wcx.springboot.demo.midware.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.RetryNTimes;

/**
 * 分布式计数器:指定一个Zookeeper数据节点作为计数器，通过更新该数据节点的内容来实现计数功能
 */
public class CounterExample {
    public static final String COUNTER_PATH = "/testSoftReference/curator/counter";

    public static void main(String[] args) {
        CuratorFramework client = ClientFactory.getClient();
        DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client, COUNTER_PATH, new RetryNTimes(3, 1000));
        try {
            Integer value = atomicInteger.get().preValue();
            System.out.println(value);
            AtomicValue<Integer> add = atomicInteger.add(1);
            System.out.println("result:" + add.succeeded());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
