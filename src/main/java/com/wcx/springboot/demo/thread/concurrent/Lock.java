package com.wcx.springboot.demo.thread.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class Lock {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        //不将获取锁的过程写入try中国，因为如果在获取锁（自定义锁实现）时发生了异常，异常抛出的同时，导致锁无故释放
        lock.lock();
        try {

        }finally {
            //在finally中释放锁，保证获取到锁之后，最终能被释放
            lock.unlock();
        }

    }
}
