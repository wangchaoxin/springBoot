package com.wcx.springboot.demo.xiangxue.interview.read_write_lock;

public class ReadWriteLockExample {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long start = System.currentTimeMillis();
                    for (int j = 0; j < 100; j++) {
                        counter.getLock();
                    }
                    long end = System.currentTimeMillis();
                    System.out.println("R"+Thread.currentThread().getName() + ":" + (end - start) / 1000 * 1000);
                }
            }).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long start = System.currentTimeMillis();
                    for (int j = 0; j < 100; j++) {
                        counter.setLock(counter.getLock() + 1);
                    }
                    long end = System.currentTimeMillis();
                    System.out.println("W"+Thread.currentThread().getName() + ":" + (end - start) / 1000 * 1000);
                }
            }).start();
            Thread.sleep(Integer.MAX_VALUE);
        }

    }
}
