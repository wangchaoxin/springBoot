package com.wcx.springboot.demo.pattern.decorator;

/**
 * 装饰器实现类
 */
public class BBeverageDecorator extends AbstractBeverageDecorator {

    private Beverage beverage;

    public BBeverageDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return 10.0 + beverage.cost();
    }
}
