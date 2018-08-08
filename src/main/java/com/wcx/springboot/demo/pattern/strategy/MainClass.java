package com.wcx.springboot.demo.pattern.strategy;

public class MainClass {
    public static void main(String[] args) {
        ADuck aDuck=new ADuck("ADuck");
        FlyBehavior flyBehavior=new AFlyBehavior();
        /*运行时动态设置行为*/
        aDuck.setFlyBehavior(flyBehavior);
        aDuck.performFly();
    }
}
