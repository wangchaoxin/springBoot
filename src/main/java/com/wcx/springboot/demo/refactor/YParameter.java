package com.wcx.springboot.demo.refactor;

public class YParameter {
    private final int a;
    private final int b;

    public YParameter(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}
