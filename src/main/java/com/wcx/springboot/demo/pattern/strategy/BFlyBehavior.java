package com.wcx.springboot.demo.pattern.strategy;

public class BFlyBehavior implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("BFlyBehavior fly");
    }
}
