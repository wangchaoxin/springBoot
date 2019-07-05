package com.wcx.springboot.demo.thread;

import java.util.Random;

public class Loop {
    public static void main(String[] args) throws InterruptedException {
        //loop result in high cpu usage
        /*while (true) {

        }*/

        //wait
        /*Object lock = new Object();
        synchronized (lock) {
            lock.wait();
        }*/

        //sleep thread

        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(r.nextInt(2));
        }


        Thread.sleep(1000000);
    }
}
