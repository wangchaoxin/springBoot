package com.wcx.springboot.demo.thread.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * The Semaphore is used for blocking thread level access to some part of the physical or logical resource.
 * A semaphore contains a set of permits; whenever a thread tries to enter the critical section,
 * it needs to check the semaphore if a permit is available or not.
 * If a permit is not available (via tryAcquire()), the thread is not allowed to jump into the critical section; however,
 * if the permit is available the access is granted, and the permit counter decreases.
 * Once the executing thread releases the critical section, again the permit counter increases (done by release() method).
 * We can specify a timeout for acquiring access by using the tryAcquire(long timeout, TimeUnit unit) method.
 * We can also check the number of available permits or the number of threads waiting to acquire the semaphore.
 */
public class SemaphoreExample {
    static Semaphore semaphore = new Semaphore(10);
    public static void main(String[] args) {

    }
    public void execute() throws InterruptedException {

        System.out.println("Available permit : " + semaphore.availablePermits());
        System.out.println("Number of threads waiting to acquire: " +
                semaphore.getQueueLength());

        if (semaphore.tryAcquire()) {
            semaphore.acquire();
            // ...
            semaphore.release();
        }

    }

    /**
     * a simple login queue to limit number users in the system:
     * tryAcquire() – return true if a permit is available immediately and acquire it otherwise return false, but acquire() acquires a permit and blocking until one is available
     * release() – release a permit
     * availablePermits() – return number of current permits available
     */
    class LoginQueueUsingSemaphore {

        private Semaphore semaphore;

        public LoginQueueUsingSemaphore(int slotLimit) {
            semaphore = new Semaphore(slotLimit);
        }
        boolean tryLogin() {
            return semaphore.tryAcquire();
        }

        void logout() {
            semaphore.release();
        }

        int availableSlots() {
            return semaphore.availablePermits();
        }
    }

    /**
     * we will first try to reach the limit and check if the next login attempt will be blocked:
     */
    @Test
    public void givenLoginQueue_whenReachLimit_thenBlocked() {
        int slots = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(slots);
        LoginQueueUsingSemaphore loginQueue = new LoginQueueUsingSemaphore(slots);
        IntStream.range(0, slots)
                .forEach(user -> executorService.execute(loginQueue::tryLogin));
        executorService.shutdown();

        assertEquals(0, loginQueue.availableSlots());
        assertFalse(loginQueue.tryLogin());
    }
    @Test
    public void givenLoginQueue_whenLogout_thenSlotsAvailable() {
        int slots = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(slots);
        LoginQueueUsingSemaphore loginQueue = new LoginQueueUsingSemaphore(slots);
        IntStream.range(0, slots)
                .forEach(user -> executorService.execute(loginQueue::tryLogin));
        executorService.shutdown();
        assertEquals(0, loginQueue.availableSlots());
        loginQueue.logout();

        assertTrue(loginQueue.availableSlots() > 0);
        assertTrue(loginQueue.tryLogin());
    }
    //4. Semaphore vs. Mutex
    /**
     * Mutex acts similarly to a binary semaphore, we can use it to implement mutual exclusion.
     * In the following example, we’ll use a simple binary semaphore to build a counter:
     */
    class CounterUsingMutex {

        private Semaphore mutex;
        private int count;

        CounterUsingMutex() {
            mutex = new Semaphore(1);
            count = 0;
        }
        void increase() throws InterruptedException {
            mutex.acquire();
            this.count = this.count + 1;
            System.out.println(Thread.currentThread().getName()+":"+count);
            Thread.sleep(1000);
            mutex.release();

        }
        int getCount() {
            return this.count;
        }
        boolean hasQueuedThreads() {
            return mutex.hasQueuedThreads();
        }
    }
    //When a lot of threads try to access the counter at once, they’ll simply be blocked in a queue:
    @Test
    public void whenMutexAndMultipleThreads_thenBlocked()
            throws InterruptedException {
        int count = 5;
        ExecutorService executorService
                = Executors.newFixedThreadPool(count);
        CounterUsingMutex counter = new CounterUsingMutex();
        IntStream.range(0, count)
                .forEach(user -> executorService.execute(() -> {
                    try {
                        counter.increase();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));
        executorService.shutdown();
        Thread.sleep(Integer.MAX_VALUE);
//        assertTrue(counter.hasQueuedThreads());
    }
    public void test() {
        //设置是否是公平锁
        Lock lock = new ReentrantLock(true);
    }
    // 请求的数量
    private static final int threadCount = 550;

    /**
     * 测试semaphore
     * Semaphore 有两种模式，公平模式和非公平模式。
     * 公平模式： 调用acquire的顺序就是获取许可证的顺序，遵循FIFO；
     * 非公平模式： 抢占式的。
     *   public Semaphore(int permits) {
     *         sync = new NonfairSync(permits);
     *     }
     *
     *     public Semaphore(int permits, boolean fair) {
     *         sync = fair ? new FairSync(permits) : new NonfairSync(permits);
     *     }
     *
     */
    public static void test2() {
        // 创建一个具有固定线程数量的线程池对象（如果这里线程池的线程数量给太少的话你会发现执行的很慢）
        ExecutorService threadPool = Executors.newFixedThreadPool(300);
        // 一次只能允许执行的线程数量。
        final Semaphore semaphore = new Semaphore(20);

        for (int i = 0; i < threadCount; i++) {
            final int threadnum = i;
            threadPool.execute(() -> {// Lambda 表达式的运用
                try {
                    semaphore.acquire();// 获取一个许可，所以可运行线程数量为20/1=20
                    test(threadnum);
                    semaphore.release();// 释放一个许可
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            });
        }
        threadPool.shutdown();
        System.out.println("finish");
    }
    public static void test(int threadnum) throws InterruptedException {
        Thread.sleep(1000);// 模拟请求的耗时操作
        System.out.println("threadnum:" + threadnum);
        Thread.sleep(1000);// 模拟请求的耗时操作
    }
}
