package com.wcx.springboot.demo.java;

import java.util.concurrent.atomic.AtomicReference;

public class RefrenceParameter {
    public static void main(String[] args) {
        Object o = "Hello";
        mutate(o);
        System.out.println(o);

        AtomicReference<Object> ref = new AtomicReference<Object>("Hello");
        mutate(ref);
        System.out.println(ref.get()); //Goodbye!



    }
    //不会改变o的值，默认为值传递
    private static void mutate(Object o) { o = "Goodbye"; } //NOT THE SAME o!

    //变为引用传递,可以改变变量值
    private static void mutate(AtomicReference<Object> ref) { ref.set("Goodbye"); }

}
