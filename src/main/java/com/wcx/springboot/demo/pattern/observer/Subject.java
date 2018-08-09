package com.wcx.springboot.demo.pattern.observer;
/**
 * 主题接口
 */
public interface Subject {
    void register(Observer observer);
    void remove(Observer observer);
    void notifyObserver(Data data);
}
