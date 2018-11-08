package com.wcx.springboot.demo.java.thread;

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
        Thread.sleep(1000000);
    }
}
