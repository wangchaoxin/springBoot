package com.wcx.springboot.demo.java8.lamda;

import com.wcx.springboot.demo.java8.fuctional_interface.CustomFunctionalInterface;

public class Client {
    public static void main(String[] args) {

        CustomFunctionalInterface c = a -> System.out.println(a);
        test(c);
    }

    public static void test(CustomFunctionalInterface c) {
        c.say("aaa");
    }
}
