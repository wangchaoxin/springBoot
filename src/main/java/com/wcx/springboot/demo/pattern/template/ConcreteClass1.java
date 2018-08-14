package com.wcx.springboot.demo.pattern.template;

/**
 * 具体实现类
 */
public class ConcreteClass1 extends AbstractClass {

    @Override
    public void operation2() {
        System.out.println("ConcreteClass1.operation2");
    }
    @Override
    boolean hook(){
        return true;
    }
}
