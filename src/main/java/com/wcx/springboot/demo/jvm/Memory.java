package com.wcx.springboot.demo.jvm;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 *  -XX:+PrintGCDetails -Xmx10m -Xms5m  -Xmn1m -XX:SurvivorRatio=2 -XX:+PrintGCDetails
 */
public class Memory {
    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    /**
     * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/a.dump
     * 内存溢出时转储快照
     */
    private static void test3() {
        for (int i = 0; i < 10; i++) {
            byte[] b = new byte[1024*1024];
        }
    }

    /**
     * -Xmn1m 设置新生代大小为1m  -XX:SurvivorRatio=2  eden/from比例
     */
    private static void test2() {
        //分配1m
        byte[] b = null;
        for (int i = 0; i < 10; i++) {
             b = new byte[1024 * 1024];
        }
    }

    /**
     * 测试-Xmx -Xms
     */
    private static void test1() {
        PrintMemoryUtil.printMemory();
        //分配1m
        byte[] b1 = new byte[1024 * 1024];
        PrintMemoryUtil.printMemory();
        //分配4m
        byte[] b2 = new byte[4 * 1024 * 1024];
        PrintMemoryUtil.printMemory();
    }

    /**
     * 直接堆内存溢出
      */
    private static void test4(){
        for (int i = 0; i < 1000; i++) {
            ByteBuffer.allocate(1024 * 1024);
        }
    }
    @Test
    public  void testString(){
        String s1 = "abc";
        String s2 = new String("abc");
        System.out.println(s1 == s2);
        //String.intern返回字符串在常量池中的引用
        System.out.println(s2.intern()==s1);
    }
}
