package com.wcx.springboot.demo.pattern.adapter;

/**
 * 被适配的类
 */
public class Adaptee {
    public void specialRequest(){
        System.out.println("Adaptee specialRequest");
    }
}
