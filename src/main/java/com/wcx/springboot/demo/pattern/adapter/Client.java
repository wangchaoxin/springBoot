package com.wcx.springboot.demo.pattern.adapter;

public class Client {
    public static void main(String[] args) {
        Target adapter = new Adapter(new Adaptee());
        adapter.request();
    }
}
