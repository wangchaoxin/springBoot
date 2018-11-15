package com.wcx.springboot.demo.thread.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class BlockingQueueExample {
    public static void main(String[] args) {
        //2. BlockingQueue Types
        //2.1. Unbounded Queue-can grow almost indefinitely
        BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>();
        //2.2. Bounded Queue
        BlockingQueue<String> blockingQueue1 = new LinkedBlockingDeque<>(10);
        //3. BlockingQueue API
        /*3.1. Adding Elements
        add() – returns true if insertion was successful, otherwise throws an IllegalStateException
        put() – inserts the specified element into a queue, waiting for a free slot if necessary
        offer() – returns true if insertion was successful, otherwise false
        offer(E e, long timeout, TimeUnit unit) – tries to insert element into a queue and waits for an available slot within a specified timeout*/
        /*3.2. Retrieving Elements
        take() – waits for a head element of a queue and removes it. If the queue is empty, it blocks and waits for an element to become available
        poll(long timeout, TimeUnit unit) – retrieves and removes the head of the queue, waiting up to the specified wait time if necessary for an element to become available. Returns null after a timeout*/



        int BOUND = 10;
        int N_PRODUCERS = 4;
        int N_CONSUMERS = Runtime.getRuntime().availableProcessors();
        int poisonPill = Integer.MAX_VALUE;
        int poisonPillPerProducer = N_CONSUMERS / N_PRODUCERS;
        int mod = N_CONSUMERS % N_PRODUCERS;

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(BOUND);

        for (int i = 1; i < N_PRODUCERS; i++) {
            new Thread(new NumbersProducer(queue, poisonPill, poisonPillPerProducer)).start();
        }

        for (int j = 0; j < N_CONSUMERS; j++) {
            new Thread(new NumbersConsumer(queue, poisonPill)).start();
        }

        new Thread(new NumbersProducer(queue, poisonPill, poisonPillPerProducer + mod)).start();

    }
    //4. Multithreaded Producer-Consumer Example


    /**
     * The important thing to remember is that we need to stop our consumer threads from waiting for an element to appear in a queue indefinitely.
     * A good technique to signal from producer to the consumer that there are no more messages to process is to send
     * a special message called a poison pill. We need to send as many poison pills as we have consumers. Then when a
     * consumer will take that special poison pill message from a queue, it will finish execution gracefully.
     */
    static class NumbersProducer implements Runnable {
        private BlockingQueue<Integer> numbersQueue;
        private final int poisonPill;
        private final int poisonPillPerProducer;

        public NumbersProducer(BlockingQueue<Integer> numbersQueue, int poisonPill, int poisonPillPerProducer) {
            this.numbersQueue = numbersQueue;
            this.poisonPill = poisonPill;
            this.poisonPillPerProducer = poisonPillPerProducer;
        }
        public void run() {
            try {
                generateNumbers();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void generateNumbers() throws InterruptedException {
            for (int i = 0; i < 100; i++) {
                numbersQueue.put(ThreadLocalRandom.current().nextInt(100));
            }
            for (int j = 0; j < poisonPillPerProducer; j++) {
                numbersQueue.put(poisonPill);
            }
        }
    }
     static class NumbersConsumer implements Runnable {
        private BlockingQueue<Integer> queue;
        private final int poisonPill;

        public NumbersConsumer(BlockingQueue<Integer> queue, int poisonPill) {
            this.queue = queue;
            this.poisonPill = poisonPill;
        }
        public void run() {
            try {
                while (true) {
                    Integer number = queue.take();
                    if (number.equals(poisonPill)) {
                        return;
                    }
                    System.out.println(Thread.currentThread().getName() + " result: " + number);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
