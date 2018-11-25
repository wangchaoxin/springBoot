package com.wcx.springboot.demo.xiangxue.interview.read_write_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Counter {
    private volatile int count = 0;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock readLock = readWriteLock.readLock();
    Lock writeLock = readWriteLock.writeLock();

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 不加锁同步，每次得到结果都不一样
     *
     * @return
     */
    public int incr() {
        return count++;
    }

    /**
     * synchronized实现同步，读的性能较差
     *
     * @return
     */
    public synchronized int incrSync() {
        return count++;
    }

    public synchronized void setSync(int value) {
        count = value;
    }

    public synchronized int getSync() {
        return count;
    }

    /**
     * 读写锁
     *
     * @return
     */
    public void setLock(int value) {
        try {
            writeLock.lock();
            Thread.sleep(5);
            count = value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public int getLock() {
        try {
            readLock.lock();
            Thread.sleep(50);
            return count;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            return count;
        }
    }

}
