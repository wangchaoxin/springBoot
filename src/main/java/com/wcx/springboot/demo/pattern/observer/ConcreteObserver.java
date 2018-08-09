package com.wcx.springboot.demo.pattern.observer;

public class ConcreteObserver implements Observer {

    private Subject subject;

    /**
     * 构造器传入主题进行构造
     * @param subject
     */
    public ConcreteObserver(Subject subject) {
        this.subject = subject;
        subject.register(this); /*构造时注册到主题上*/
    }

    @Override
    public void update(Data data) {
        System.out.println("update data,seq");
    }
}
