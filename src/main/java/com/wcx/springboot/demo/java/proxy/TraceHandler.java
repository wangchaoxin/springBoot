package com.wcx.springboot.demo.java.proxy;

import com.wcx.springboot.demo.java.reflector.A;
import com.wcx.springboot.demo.java.reflector.inter.IA;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TraceHandler implements InvocationHandler {
    private Object target;

    public TraceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());
        for (Object arg : args) {
            System.out.println(arg);
        }
        return method.invoke(target, args);
    }

    public static void main(String[] args) {
        A a=new A();
        TraceHandler traceHandler = new TraceHandler(a);
        Class<?>[] interfaces = a.getClass().getInterfaces();
        Object proxy = Proxy.newProxyInstance(null, interfaces, traceHandler);
        if (proxy instanceof IA) {
            ((IA) proxy).print("testSoftReference");
        }
    }
}
