package com.wcx.springboot.demo.pattern.singleton;

public class Singleton {

    /**私有成员变量*/
    private static Singleton instance;

    /**
     * 构造函数私有
     */
    private Singleton() {
    }

    /**
     * 加synchronized不好的原因：只有第一次执行此方法时，才需要同步，每次调用都同步没有必要
     * @return
     */
    public synchronized static Singleton getInstance() {
        /*延迟初始化，用的时候才使用*/
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
