package com.wcx.springboot.demo.midware.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 分布式锁：为了保证数据一致性，在某个运行点（减库操作，流水号生成）需要同步控制
 */
public class LockExample {
    private static final String LOCK_PATH = "/testSoftReference/curator/lock";

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CuratorFramework client = ClientFactory.getClient();
        final InterProcessMutex lock = new InterProcessMutex(client, LOCK_PATH);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                        //加锁
                        lock.acquire();
                    } catch (Exception e) {
                    }
                    //如果不加锁，会生成相同的流水号
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    System.out.println("生成的订单号是：" + orderNo);
                    try {
                        //解锁
                        lock.release();
                    } catch (Exception e) {
                    }
                }
            }).start();
        }
        countDownLatch.countDown();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
