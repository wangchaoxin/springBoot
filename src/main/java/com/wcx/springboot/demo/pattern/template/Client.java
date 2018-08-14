package com.wcx.springboot.demo.pattern.template;

public class Client {
    public static void main(String[] args) {
        AbstractClass a1=new ConcreteClass1();
        a1.templateMethod();
    }
}
