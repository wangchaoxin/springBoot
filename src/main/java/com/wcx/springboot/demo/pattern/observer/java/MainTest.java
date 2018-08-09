package com.wcx.springboot.demo.pattern.observer.java;

import com.wcx.springboot.demo.pattern.observer.normal.ConcreteData;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式
 */
public class MainTest {


    public static void main(String[] args) {
        Observable os1=new ConcreteObserverable();
        Observer o1=new ConcreteObserver(os1);
        Observer o2=new ConcreteObserver(os1);
        ((ConcreteObserverable) os1).setData(new ConcreteData(1));
    }
}
