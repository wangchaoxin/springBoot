package com.wcx.springboot.demo.thread.concurrent;

import java.util.concurrent.ThreadFactory;

/**
 * hreadFactory acts as a thread (non-existing) pool which creates a new thread on demand.
 * It eliminates the need of a lot of boilerplate coding for implementing efficient thread creation mechanisms.
 */
public class ThreadFactoryExample {

    public static void main(String[] args) {
        BaeldungThreadFactory factory = new BaeldungThreadFactory(
                "BaeldungThreadFactory");
        for (int i = 0; i < 10; i++) {
            Thread t = factory.newThread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
            t.start();
        }
    }
}
 class BaeldungThreadFactory implements ThreadFactory {
    private int threadId;
    private String name;

    public BaeldungThreadFactory(String name) {
        threadId = 1;
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name + "-Thread_" + threadId);
        System.out.println("created new thread with id : " + threadId +
                " and name : " + t.getName());
        threadId++;
        return t;
    }
}
