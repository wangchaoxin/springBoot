package com.wcx.springboot.demo.java.Reflector.inter;

import java.io.Serializable;

public interface IA extends Serializable{  //继承接口
    int NUM = 1;   //public static final constant,接口中的变量都是public static final类型的
    void print(String args);
}
