package com.wcx.springboot.demo.spring.aop.cglib;

import com.wcx.springboot.demo.spring.aop.proxy.RequestableImpl;
import net.sf.cglib.proxy.Enhancer;

public class Client {
    public static void main(String[] args) {
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(BaseClass.class);
        enhancer.setCallback(new RequestCallback());

        BaseClass proxy = (BaseClass)enhancer.create();
        proxy.request();
    }
}
