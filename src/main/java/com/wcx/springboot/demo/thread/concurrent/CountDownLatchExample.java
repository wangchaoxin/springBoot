package com.wcx.springboot.demo.thread.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Essentially, by using a CountDownLatch we can cause a thread to block until other threads have completed a given task.
 * If we were doing some parallel processing, we could instantiate the CountDownLatch with the same value for the counter as
 * a number of threads we want to work across. Then, we could just call countdown() after each thread finishes,
 * guaranteeing that a dependent thread calling await() will block until the worker threads are finished.
 */
public class CountDownLatchExample {
    public static void main(String[] args) {

    }

    // 请求的数量
    private static final int threadCount = 550;

    public static void test1() throws InterruptedException {
        // 创建一个具有固定线程数量的线程池对象（如果这里线程池的线程数量给太少的话你会发现执行的很慢）
        ExecutorService threadPool = Executors.newFixedThreadPool(300);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadnum = i;
            threadPool.execute(() -> {// Lambda 表达式的运用
                try {
                    test(threadnum);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();// 表示一个请求已经被完成
                }

            });
        }
        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("finish");
    }
    public static void test(int threadnum) throws InterruptedException {
        Thread.sleep(1000);// 模拟请求的耗时操作
        System.out.println("threadnum:" + threadnum);
        Thread.sleep(1000);// 模拟请求的耗时操作
    }
    /**
     *  Waiting for a Pool of Threads to Complete
     */
    @Test
    public void whenParallelProcessing_thenMainThreadWillBlockUntilCompletion()
            throws InterruptedException {

        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch countDownLatch = new CountDownLatch(5);
        List<Thread> workers = Stream
                .generate(() -> new Thread(new Worker(outputScraper, countDownLatch)))
                .limit(5)
                .collect(toList());

        workers.forEach(Thread::start);
        countDownLatch.await();
        outputScraper.add("Latch released");

        //5. Terminating a CountdownLatch Early
        /*Sometimes, we may run into a situation where the Workers terminate in error before counting down the
        CountDownLatch. This could result in it never reaching zero and await() never terminating:*/
        boolean completed = countDownLatch.await(3L, TimeUnit.SECONDS);
    }


    public class Worker implements Runnable {
        private List<String> outputScraper;
        private CountDownLatch countDownLatch;

        public Worker(List<String> outputScraper, CountDownLatch countDownLatch) {
            this.outputScraper = outputScraper;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            outputScraper.add("Counted down");
            countDownLatch.countDown();
        }
    }
}
