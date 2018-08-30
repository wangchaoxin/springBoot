package com.wcx.springboot.demo.java.generic;

/**
 * 泛型类
 * <p>
 * 泛型类型在逻辑上看以看成是多个不同的类型，实际上都是相同的基本类型
 *
 * @param <T>
 */
public class Box<T> implements IBox<T> {
    private T data;

    public Box(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public void init() {
        System.out.println("init");
    }
}
