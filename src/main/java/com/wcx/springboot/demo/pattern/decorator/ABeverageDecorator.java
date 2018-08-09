package com.wcx.springboot.demo.pattern.decorator;

/**
 * 装饰器实现类
 */
public class ABeverageDecorator extends AbstractBeverageDecorator {
    private Beverage beverage;

    public ABeverageDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return 3.0 + beverage.cost();
    }
}
