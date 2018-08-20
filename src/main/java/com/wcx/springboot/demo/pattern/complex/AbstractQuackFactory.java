package com.wcx.springboot.demo.pattern.complex;

/**
 * 抽象工厂模式：创建鸭子家族工厂
 */
public abstract class AbstractQuackFactory {
    abstract Quackable createAQuack();
    abstract Quackable createBQuack();
}
