package com.wcx.springboot.demo.xiangxue.interview.redis;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class CacheService {

    private final ConcurrentHashMap<String, ReentrantLock> locks = new ConcurrentHashMap();

    private StringRedisTemplate template;

    /**
     * 也可以将lock存在map中
     * @param key
     * @return
     */
    private ReentrantLock getLockForKey(String key) {
        ReentrantLock lock = new ReentrantLock();
        ReentrantLock previous = locks.putIfAbsent(key, lock);
        return previous == null ? lock : previous;
    }

    /**
     * 释放锁
     * @param key
     */
    private void releaseLock(String key) {
        ReentrantLock lock = getLockForKey(key);
        //是不是当前线程持有
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    /**
     * 更新缓存操作
     * @param key
     */
    public void get(String key) {
        //1.删除缓存
        template.delete(key);
        //2.锁住key,多个线程同时到达，只有一个线程可以更新，避免缓存雪崩,更新缓存同步控制
        //后续线程直接从缓存拿
       /* Lock lockForKey = getLockForKey(key);
        lockForKey.lock();*/
        synchronized (key) {
            //3.读取数据库
            //4.设置缓存
        }
    }
}
