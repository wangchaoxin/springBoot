package com.wcx.springboot.demo.java.generic;

/**
 * 泛型接口
 *
 * @param <T>
 */
public interface IBox<T> {
    void setData(T data);
}
