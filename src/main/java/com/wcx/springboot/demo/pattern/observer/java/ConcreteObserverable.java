package com.wcx.springboot.demo.pattern.observer.java;

import com.wcx.springboot.demo.pattern.observer.normal.Data;

import java.util.Observable;

/**
 * java实现方式：主题对象，继承Observable（可观察的）
 */
public class ConcreteObserverable extends Observable {
    private Data data;

    public void setData(Data data) {
        this.data = data;
        setChanged(); /*标记为已改变状态*/
        notifyObservers(data); /*通知观察者,传递参数*/
    }
}
