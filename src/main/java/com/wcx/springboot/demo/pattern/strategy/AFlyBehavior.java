package com.wcx.springboot.demo.pattern.strategy;

public class AFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("AFlyBehavior fly");
    }
}
