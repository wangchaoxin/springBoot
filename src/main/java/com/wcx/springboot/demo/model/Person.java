package com.wcx.springboot.demo.model;

public abstract class Person {
    private String name;
    public abstract String getDesc();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
