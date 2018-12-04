package com.wcx.springboot.demo.midware.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BarrierExample {
    //所有线程都准备好才执行的逻辑,只能存在于同一个JVM
    public static CyclicBarrier barrier = new CyclicBarrier(3);

    public static final String BARRIER_PATH = "/testSoftReference/curator/barrier";

    static DistributedBarrier distributedBarrier;

    static DistributedDoubleBarrier distributedDoubleBarrier;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CuratorFramework client = ClientFactory.getClient();
        distributedBarrier = new DistributedBarrier(client, BARRIER_PATH);

        //可以设置线程数量的barrier
        distributedDoubleBarrier = new DistributedDoubleBarrier(client, BARRIER_PATH, 3);
        //在enter和leave中添加代码

        executorService.submit(new Runner("Thread 1"));
        executorService.submit(new Runner("Thread 2"));
        executorService.submit(new Runner("Thread 3"));


        //分布式barrier
       /* CuratorFramework client = ClientFactory.getClient();
        distributedBarrier = new DistributedBarrier(client, BARRIER_PATH);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        distributedBarrier.setBarrier();
                        distributedBarrier.waitOnBarrier();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }*/
        Thread.sleep(2000);
        //释放barrirer
        distributedBarrier.removeBarrier();
    }

    static class Runner implements Runnable {

        private String name;

        public Runner(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + "准备好了");
            try {
                //jvm barrier
                //barrier.await();

                //distributedBarrier
                //完成barrier设置
                distributedBarrier.setBarrier();
                //等待释放
                distributedBarrier.waitOnBarrier();

                //distributedDoubleBarrier
                //进入的线程到达三个触发enter
                distributedDoubleBarrier.enter();
                //退出的线程到达三个触发leave
                distributedDoubleBarrier.leave();

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(name + "起跑");
        }
    }
}
