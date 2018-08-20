package com.wcx.springboot.demo.pattern.proxy.dynamic_proxy;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        IPerson person=new Person();
        IPerson personProxy = (IPerson)Proxy.newProxyInstance(person.getClass().getClassLoader(), person.getClass().getInterfaces(),
                new PersonInvocationHandler(person));
        personProxy.getAge();
        personProxy.setAge(1);
    }
}
