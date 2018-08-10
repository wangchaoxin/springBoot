package com.wcx.springboot.demo.pattern.singleton;

/**
 * 线程安全单例，但是不是懒加载
 */
public class Singleton1 {

    public static final Singleton1 instance = new Singleton1();

    /**
     * 构造函数必须私有
     */
    private Singleton1(){}
}
