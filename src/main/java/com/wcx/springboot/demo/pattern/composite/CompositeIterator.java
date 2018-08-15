package com.wcx.springboot.demo.pattern.composite;

import com.wcx.springboot.demo.pattern.iterator.Iterator;

public class CompositeIterator implements Iterator {

    private Iterator iterator;

    public CompositeIterator(Iterator iterator) {
        this.iterator = iterator;
    }

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
