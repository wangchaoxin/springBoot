package com.wcx.springboot.demo.pattern.observer.java;

import com.wcx.springboot.demo.pattern.observer.normal.Data;

import java.util.Observable;
import java.util.Observer;

/**
 * java实现方式：观察者对象，实现Observer接口
 */
public class ConcreteObserver implements Observer,ObserverOperation {
    private Observable observable;

    /**
     * 注入subject，注册到subject中
     * @param observable
     */
    public ConcreteObserver(Observable observable){
        this.observable=observable;
        observable.addObserver(this);
    }
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Data){
            System.out.println(((Data) arg).getVersion());
        }
    }

    @Override
    public void delete() {
        this.observable.deleteObserver(this);
    }
}
