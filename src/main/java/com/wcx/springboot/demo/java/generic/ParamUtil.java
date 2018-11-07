package com.wcx.springboot.demo.java.generic;

public class ParamUtil {
    /**
     * 泛型方法
     * @param params
     * @param <T>
     * @return
     */
    public <T extends Number>  boolean isValueGtZero(T... params) {
        for (T param : params) {
            if (param.doubleValue()< 0) {
                return false;
            }
        }
        return true;
    }
}
