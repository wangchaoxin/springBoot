package com.wcx.springboot.demo.java.Reflector;

import com.wcx.springboot.demo.java.Reflector.inter.IA;

public class A implements IA {



    public A() {
        NestedA nestedA = new NestedA();
    }

    @Override
    public void print(String args) {
        System.out.println(args);
    }


    /**
     * 静态内部类
     */
    public static class NestedA {

    }
}
