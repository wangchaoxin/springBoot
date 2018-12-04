package com.wcx.springboot.demo.jvm.performance.builder;

/**
 * 构造器参数太多怎么办：用build模式
 * 使用场景：参数过多，参数有可能变化
 */
public class Parameter {

    private int a;
    private int b;
    private int c;

    /**
     * 函数参数过多，可以附默认值
     */
    public void test1(int a, int b, int c) {

    }
    public void test1(int a, int b) {
        this.test1(a, b, 0);
    }

    public void test1(int a) {
        this.test1(a, 0, 0);
    }
}
