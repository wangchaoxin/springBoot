package com.wcx.springboot.demo.spring.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RequestInvocationHandler implements InvocationHandler {

    private Object target;

    public RequestInvocationHandler(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("request")) {
            System.out.println("before");
            Object result = method.invoke(target, args);
            System.out.println("after");
            return result;
        }
        return null;
    }
}
