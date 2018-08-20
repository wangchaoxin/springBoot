package com.wcx.springboot.demo.pattern.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 实际的处理类
 */
public class PersonInvocationHandler implements InvocationHandler {

    private IPerson person;

    public PersonInvocationHandler(IPerson person) {
        this.person = person;
    }

    /**
     * 代理控制方法的调用
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().startsWith("get")){
            System.out.println("execute get");
            return method.invoke(person,args);
        } else if (method.getName().startsWith("set")) {
            System.out.println("execute set");
            throw new IllegalAccessException();
        }else {
            return method.invoke(person, args);
        }
    }
}
