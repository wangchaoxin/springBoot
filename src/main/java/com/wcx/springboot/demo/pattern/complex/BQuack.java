package com.wcx.springboot.demo.pattern.complex;

public class BQuack implements Quackable{
    @Override
    public void quack() {
        System.out.println("BQuack quack");
    }
}
