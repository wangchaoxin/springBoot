package com.wcx.springboot.demo.pattern.strategy;

public class ADuck extends Duck {

    public ADuck() {
        super();
    }

    public ADuck(String name) {
        super(name);
    }

    @Override
    public void swim() {
        System.out.println("ADuck swim");
    }

    @Override
    public void display() {
        System.out.println("ADcuk display");
    }
}
