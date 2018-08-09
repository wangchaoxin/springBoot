package com.wcx.springboot.demo.pattern.singleton;

public class Singleton {

    /**私有成员变量*/
    private static Singleton instance;

    /**
     * 构造函数私有
     */
    private Singleton() {
    }

    public static Singleton getInstance() {
        /*延迟初始化，用的时候才使用*/
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
