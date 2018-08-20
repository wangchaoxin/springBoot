package com.wcx.springboot.demo.pattern.complex;

/**
 * 包装工厂：创建每个Quack的包装类工厂
 */
public class QuackDocoratorFacotry extends AbstractQuackFactory {
    @Override
    Quackable createAQuack() {
        return new QuackDecorator(new AQuack());
    }

    @Override
    Quackable createBQuack() {
        return new QuackDecorator(new BQuack());
    }
}
