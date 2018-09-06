package com.wcx.springboot.demo.pattern.factory;

public class AnyFactory {
    public static <T> T create(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        AServiceImpl aService = AnyFactory.create(AServiceImpl.class);
        System.out.println("end");
    }
}
