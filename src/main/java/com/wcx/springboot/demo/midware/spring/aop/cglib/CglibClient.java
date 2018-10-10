package com.wcx.springboot.demo.midware.spring.aop.cglib;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CglibClient {
    public static void main(String[] args) {
//        BaseClass proxy = createClass(BaseClass.class);
//        proxy.request();

        BaseClass aClass = createClass(BaseClass.class);
        BaseClass bClass = createClass(BaseClass.class);
        System.out.println(aClass.getClass());
        System.out.println(bClass.getClass());
        try {
//            beanCreator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BaseClass createClass(Class c) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(c);
        enhancer.setCallback(new RequestCallback());
        return (BaseClass) enhancer.create();
    }

    /**
     * beanGenerator
     * Another useful construct from the cglib is a BeanGenerator class.
     * It allows us to dynamically create beans and to add fields together with setter and getter methods.
     * It can be used by code generation tools to generate simple POJO objects
     */
    public static void beanCreator() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.addProperty("name", String.class);
        Object myBean = beanGenerator.create();
        Object aClass = beanGenerator.createClass();
        Object myBean1 = beanGenerator.create();  //创建出来的是同一个类
        System.out.println(String.format("myBean [%s],\nmyBean1 [%s]", myBean.getClass(), myBean1.getClass()));
        Method setter = myBean.getClass().getMethod("setName", String.class);
        setter.invoke(myBean, "some string value set by a cglib");
        Method getter = myBean.getClass().getMethod("getName");

    }

}
