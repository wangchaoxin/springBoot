package com.wcx.springboot.demo.jvm;

import java.util.concurrent.CountDownLatch;

public class LocalVariable {

    private static int count = 0;

    public static class User{
        private int age = 0;
        private String name = "";
    }

    /**
     * 局部变量多，局部变量表大，导致调用次数减少
     * 局部变量少的函数可用支持更深的函数调用
     */
    public static void recursion() {
        int a = 1, b = 1, c = 1, d = 4, e = 1, f = 1, g = 1, a1 = 1;
        count++;
        recursion();
    }

    /**
     * 没有局部变量，调用次数多
     */
    public static void recursion1() {
        count++;
        recursion();
    }

    /**
     * -XX:+PrintGC  打印GC日志    -XX:+PrintGCDetails
     * -XX:+PrintHeapAtGC  在GC的时候打印堆栈信息
     *  -XX:+PrintGCTimeStamps  每次GC发生时，额外输出GC发生时间，该输出时间为虚拟机启动后的时间偏移量
     */
    public static void printGc(){
        byte[] a = new byte[1024 * 1024 * 6];
        a = null;
        System.gc();
    }

    /**
     * 申请6m空间
     */
    public static void generateSpace() {
        byte[] a = new byte[1024 * 1024 * 6];
    }

    public static void main(String[] args) {
        try {
//            printGc();
//            recursion1();

            for (int i = 0; i < 10000000; i++) {
                generateSpace();
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            countDownLatch.await();
        } catch (Throwable e) {
            System.out.println("recursion count:" + count);
        }
    }
}
