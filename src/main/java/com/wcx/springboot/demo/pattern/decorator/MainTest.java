package com.wcx.springboot.demo.pattern.decorator;

public class MainTest {
    public static void main(String[] args) {
        Beverage beverage = new BaseBeverage();
        beverage = new ABeverageDecorator(beverage);
        beverage = new BBeverageDecorator(beverage);
        System.out.println(beverage.cost());
    }
}
