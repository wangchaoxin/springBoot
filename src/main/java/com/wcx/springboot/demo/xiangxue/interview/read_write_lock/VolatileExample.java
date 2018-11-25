package com.wcx.springboot.demo.xiangxue.interview.read_write_lock;

import java.util.Random;

/**
 * 一写多读，可以用volatile关键字
 */
public class VolatileExample {
    public static volatile int count = 0;

    public static void main(String[] args) throws InterruptedException {

        Random r = new Random();
        Thread t1 = new Thread(new R1());
        Thread t2 = new Thread(new R1());
        Thread t3 = new Thread(new R1());
        Thread t4 = new Thread(new R1());
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        while (true) {
            count = count + r.nextInt(100);
            System.out.println("main " + count);
            Thread.sleep(10);
        }
    }

    public static class R1 implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName() +
                        "," + count);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
