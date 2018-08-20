package com.wcx.springboot.demo.pattern.complex;

/**
 * quack工厂
 */
public class QuackFactory extends AbstractQuackFactory {
    @Override
    Quackable createAQuack() {
        return new AQuack();
    }

    @Override
    Quackable createBQuack() {
        return new BQuack();
    }
}
