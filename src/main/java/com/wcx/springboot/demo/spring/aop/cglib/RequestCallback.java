package com.wcx.springboot.demo.spring.aop.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 要想对类进行扩展，需要实现Callback接口，更多时候直接用MethodInterceptor接口，该接口扩展Callback接口
 */
public class RequestCallback implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (method.getName().equals("request")) {
            System.out.println("RequestCallback");
            return methodProxy.invokeSuper(o, args);
        }
        return null;
    }
}
