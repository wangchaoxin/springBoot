package com.wcx.springboot.demo.pattern.iterator;

public interface Iterator {
    boolean hasNext();
    Object next();
    void remove();
}
