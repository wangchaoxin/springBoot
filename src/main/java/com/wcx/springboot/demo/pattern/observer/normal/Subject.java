package com.wcx.springboot.demo.pattern.observer.normal;
/**
 * 主题接口
 */
public interface Subject {
    void register(Observer observer);
    void remove(Observer observer);
    void notifyObserver(Data data);
}
