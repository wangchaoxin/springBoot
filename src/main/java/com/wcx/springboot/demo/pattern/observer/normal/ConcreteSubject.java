package com.wcx.springboot.demo.pattern.observer.normal;

import java.util.ArrayList;
import java.util.List;


public class ConcreteSubject implements Subject {

    /**数据对象*/
    private Data data;
    /**观察者对象*/
    List<Observer> observers = new ArrayList<>();

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(Data data) {
        for (Observer observer : observers) {
            observer.update(data);
        }
    }

    public void update(Data data) {
        notifyObserver(data);
    }

    /**
     * 数据变更时更新观察者
     * @param data
     */
    public void setData(Data data) {
        this.data = data;
        update(data);
    }
}
