package com.wcx.springboot.demo.spring.aop.proxy;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {

        IRequestable requestable = (IRequestable) Proxy.newProxyInstance(Client.class.getClassLoader(), IRequestable.class.getInterfaces(),
                new RequestInvocationHandler(new RequestableImpl()));

    }
}
