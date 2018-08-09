package com.wcx.springboot.demo.pattern.observer.normal;

public class MainTest {
    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observer o1 = new ConcreteObserver(subject);
        Observer o2 = new ConcreteObserver(subject);
        Data data = new ConcreteData(1);
        ((ConcreteSubject) subject).update(data);
    }
}
