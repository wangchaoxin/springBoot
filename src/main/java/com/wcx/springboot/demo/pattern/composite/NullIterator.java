package com.wcx.springboot.demo.pattern.composite;

import com.wcx.springboot.demo.pattern.iterator.Iterator;

/**
 * 空迭代器，在leaf中使用，可以让leaf中的createIterator方法中返回
 */
public class NullIterator implements Iterator {
    /**
     * hasNext永远返回false，相当于没有起作用
     * @return
     */
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }

    @Override
    public void remove() {

    }
}
