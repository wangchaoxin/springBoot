package com.wcx.springboot.demo.pattern.strategy;


/**
 * 策略模式:定义了算法族，分别封装起来，让他们之间可以相互替换
 */
public abstract class Duck {

    /*将行为变量添加到父类中,子类拥有该行为*/
    private FlyBehavior flyBehavior;
    private QuakeBehavior quakeBehavior;

    private String name;

    Duck() {

    }

    Duck(String name) {
        this.name = name;
    }

    public abstract void swim();

    public abstract void display();

    /*这两个接口是变化的，不同的实现类需要有不同的功能组合，将变化的地方分离开，抽象出
     * 新的实现类*/
//    public abstract void fly();
//    public abstract void QuakeBehavior();

    /*新添加两个方法，子类不用覆盖*/
    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuake() {
        quakeBehavior.quake();
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuakeBehavior(QuakeBehavior quakeBehavior) {
        this.quakeBehavior = quakeBehavior;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
