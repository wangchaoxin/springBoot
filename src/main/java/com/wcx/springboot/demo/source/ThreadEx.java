package com.wcx.springboot.demo.source;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadEx {

    /**
     * 线程安全容器
     */
    public void test1() {
        Collections.synchronizedList(new ArrayList<>());
        Collections.synchronizedMap(new HashMap<>());
        Collections.synchronizedSet(new HashSet<>());
    }

    /**
     * condition 使用
     *
     * @throws InterruptedException
     */
    @Test
    public void test2() throws InterruptedException {
        try {
            Lock lock = new ReentrantLock();
            Condition condition = lock.newCondition();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    task(lock, condition);
                }
            });
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    task(lock, condition);
                }
            });
            t.start();
            t1.start();
            Thread.sleep(1000);
            lock.lock();
            //唤醒所有线程
//            condition.signalAll();
            //只唤醒一个线程
            condition.signal();
            lock.unlock();
            System.out.println("main thread end");
           /* while (Thread.activeCount() < 0) {
                //当前线程让出cpu
                Thread.yield();
            }*/
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void task(Lock lock, Condition condition) {
        try {
            System.out.println(Thread.currentThread().getName() + " entered");
            lock.lock();
            //wait的时候释放锁
            condition.await(10, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + " exit");
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }
}
