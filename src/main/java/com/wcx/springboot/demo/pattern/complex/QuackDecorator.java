package com.wcx.springboot.demo.pattern.complex;

/**
 * 装饰器模式:需求：统计所有鸭子呱呱叫次数，加入一些新的行为
 */
public class QuackDecorator implements Quackable {

    private Quackable quackable;
    /**
     * 计数器字段
     */
    private static int count = 0;

    public QuackDecorator(Quackable quackable) {
        this.quackable = quackable;
    }

    @Override
    public void quack() {
        quackable.quack();
        count++;
    }

    public static int getCount()
    {
        return count;
    }

}
