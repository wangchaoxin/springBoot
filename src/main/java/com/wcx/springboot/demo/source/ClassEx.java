package com.wcx.springboot.demo.source;

public class ClassEx {
    public void test1() {
        //获取 application classloader
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

        //父类加载器请求子类加载器去完成类加载动作，spi的实现方式,JNDI服务使用这个线程上下文类加载器去加载所需要的spi代码
        Thread.currentThread().setContextClassLoader(null);
    }
}
