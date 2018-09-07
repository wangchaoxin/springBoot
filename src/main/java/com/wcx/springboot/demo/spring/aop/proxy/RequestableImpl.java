package com.wcx.springboot.demo.spring.aop.proxy;

public class RequestableImpl implements IRequestable {
    @Override
    public int request() {
        System.out.println("request");
        return 1;
    }
}
