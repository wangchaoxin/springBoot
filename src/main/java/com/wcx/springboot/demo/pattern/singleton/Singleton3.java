package com.wcx.springboot.demo.pattern.singleton;

/**
 * 内部类:线程安全，JVM保证线程安全
 */
public class Singleton3 {

    private static class InstanceHolder {
        static Singleton3 instance = new Singleton3();
    }

    public static Singleton3 getInstance() {
        return InstanceHolder.instance;
    }
}
