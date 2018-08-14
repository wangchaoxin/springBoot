package com.wcx.springboot.demo.pattern.adapter;

/**
 * 适配器类
 */
public class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specialRequest();
    }
}
