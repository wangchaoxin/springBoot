package com.wcx.springboot.demo.java.reflector;

import com.wcx.springboot.demo.java.reflector.inter.IA;

public class A implements IA {



    public A() {
        NestedA nestedA = new NestedA();
    }


    public int getInt(){
        return 1;
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
