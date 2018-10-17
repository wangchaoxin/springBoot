package com.wcx.springboot.demo.jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * stop the world 案例
 * -XX:+PrintGCDetails -Xmx1g -Xms1g  -Xmn512k  -XX:+PrintGCDetails
 */
public class StopTheWorld {
    public static class MyThread extends Thread {
        Map map = new HashMap();

        @Override
        public void run() {
            while (true) {
                //内存消耗500m时，清空内存
                if (map.size() * 512 / 1024 / 1024 >= 900) {
                    map.clear();
                    System.out.println("clean map");
                }
                byte[] b1;
                for (int i = 0; i < 100; i++) {
                    b1 = new byte[512];
                    map.put(System.nanoTime(), b1);
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class PrintThread extends Thread {
        public static final long startTime = System.currentTimeMillis();

        @Override
        public void run() {
            while (true) {
                long t = System.currentTimeMillis() - startTime;
                System.out.println(t / 1000 + "." + t % 1000);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();
        PrintThread p = new PrintThread();
        t.start();
        p.start();
    }
}
