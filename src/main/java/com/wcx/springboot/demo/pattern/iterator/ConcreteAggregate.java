package com.wcx.springboot.demo.pattern.iterator;

import java.util.ArrayList;
import java.util.List;

public class ConcreteAggregate implements Aggregate {

    private Object[] array; //可以定义新的iterator遍历array
    private List list = new ArrayList();


    /**
     * 外部获取iterator，遍历该集合
     * @return
     */
    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(list);
    }
}
