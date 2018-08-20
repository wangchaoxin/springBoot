package com.wcx.springboot.demo.pattern.proxy.dynamic_proxy;

public class Person implements IPerson {

    private String name;
    private int age;

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
