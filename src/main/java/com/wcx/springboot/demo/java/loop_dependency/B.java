package com.wcx.springboot.demo.java.loop_dependency;

public class B {
    // private A a = new A();
    private A a;

    public void setA(A a) {
        this.a = a;
    }
}
