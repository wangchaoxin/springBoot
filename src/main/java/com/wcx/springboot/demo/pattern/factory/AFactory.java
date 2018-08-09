package com.wcx.springboot.demo.pattern.factory;

/**
 * 工厂方法
 */
public class AFactory {
    public static CommonService createService(){
        return new AServiceImpl();
    }
}
