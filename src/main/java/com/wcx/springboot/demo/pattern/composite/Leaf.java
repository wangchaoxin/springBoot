package com.wcx.springboot.demo.pattern.composite;

import com.wcx.springboot.demo.pattern.iterator.Iterator;

public class Leaf extends Component {

    public void operationLeaf() {
        System.out.println("Leaf.operationLeaf");
    }

    /**
     * 可以直接返回null，但是客户端代码就需要判断是否返回null
     * @return
     */
    public Iterator createIterator() {
        return new NullIterator();
    }
}
