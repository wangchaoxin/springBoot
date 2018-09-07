package com.wcx.springboot.demo.java8.fuctional_interface;

/**
 * define: 只含有一个接口的接口
 * purpose:为了当做lamda表达式使用
 * FunctionalInterface annotation:简单声明作用，检查是否是一个函数接口，如果不是编译器报错
 */
@FunctionalInterface
public interface CustomFunctionalInterface {
    abstract void say(String s);
}
