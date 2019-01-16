package com.wcx.springboot.demo.java.exception;

/**
 * 继承RuntimeException,throw的时候不用方法声明异常
 * 非检查类型异常，可以写再service里，在controller中catch异常进行处理
 * controller不写业务逻辑，service中进行参数检查抛出异常
 * 2 spring aop进行统一异常处理
 */
public class AException extends RuntimeException {
}
