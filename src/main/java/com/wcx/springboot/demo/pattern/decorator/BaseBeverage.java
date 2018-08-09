package com.wcx.springboot.demo.pattern.decorator;

/**
 * 实际被装饰的类
 */
public class BaseBeverage extends Beverage {

    public BaseBeverage(){}

    @Override
    public double cost() {
        return 0;
    }
}
