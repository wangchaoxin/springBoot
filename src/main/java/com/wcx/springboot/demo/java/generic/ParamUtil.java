package com.wcx.springboot.demo.java.generic;

public class ParamUtil {
    /**
     * 泛型方法
     * @param params
     * @param <T>
     * @return
     */
    public static  <T extends Number>  boolean isValueGtZero(T... params) {
        for (T param : params) {
            if (param.doubleValue()< 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        isValueGtZero(1, 2, 3);
    }
}
