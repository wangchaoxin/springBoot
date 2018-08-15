package com.wcx.springboot.demo.pattern.iterator;

import java.util.List;

/**
 * iterator实现类
 */
public class ConcreteIterator implements Iterator {

    private List list;

    public ConcreteIterator(List list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return list.iterator().hasNext();
    }

    @Override
    public Object next() {
        return list.iterator().next();
    }

    @Override
    public void remove() {
        list.iterator().remove();
    }
}
