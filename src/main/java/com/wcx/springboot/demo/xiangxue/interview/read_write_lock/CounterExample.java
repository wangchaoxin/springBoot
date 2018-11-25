package com.wcx.springboot.demo.xiangxue.interview.read_write_lock;

import java.util.concurrent.CountDownLatch;

/**
 * 多线程计数器
 */
public class CounterExample {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        //1.多个线程同时写
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        counter.incrSync();
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        System.out.println("suppose counter :5000,actual counter:" + counter.getCount());

        countDownLatch.await();
        Thread.sleep(Integer.MAX_VALUE);
    }

}
