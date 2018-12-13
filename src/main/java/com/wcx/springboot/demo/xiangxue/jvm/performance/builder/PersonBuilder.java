package com.wcx.springboot.demo.xiangxue.jvm.performance.builder;

public abstract class PersonBuilder {
    private int age;
    private String name;

    public abstract PersonBuilder buildName(String name);

    public abstract PersonBuilder builAge(int age);

    public abstract Person build();




}
