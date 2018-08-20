package com.wcx.springboot.demo.pattern.complex;

public class AQuack implements Quackable {
    @Override
    public void quack() {
        System.out.println("Aquack quack");
    }
}
