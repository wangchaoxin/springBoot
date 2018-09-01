package com.wcx.springboot.demo.refactor;

public class YDelegate {
    static int y = 0;

    public int add(YParameter YParameter) {
        return YParameter.getA() + YParameter.getB();
    }
}