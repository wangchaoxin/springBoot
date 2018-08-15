package com.wcx.springboot.demo.pattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合节点
 */
public class Composite {

    /*组合节点包含Component集合，元素可以是leaf或者Composite*/
    private List<Component> list = new ArrayList();

    public void operationComposite() {
        System.out.println("Composite.operationComposite");
    }

    public void add(Component component) {
        list.add(component);
    }

    public void remove(Component component)  {
        list.remove(component);
    }

}
