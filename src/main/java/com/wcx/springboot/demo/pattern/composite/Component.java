package com.wcx.springboot.demo.pattern.composite;

import com.wcx.springboot.demo.pattern.iterator.Iterator;

/**
 * 组合节点与叶节点的基类，当基类中有些方法可能不适合某种节点，面对这种情况，你最好抛出运行时异常
 */
public class Component {


    public Component() {
    }

    /**
     * 叶节点实现的操作
     *
     * @throws Exception
     */
    public void operationLeaf() throws Exception {
        throw new Exception();
    }

    /**
     * 组合节点实现的操作
     *
     * @throws Exception
     */
    public void operationComposite() throws Exception {
        throw new Exception();
    }

    /**
     * 添加节点，组合节点使用
     * @throws Exception
     */
    public void add(Component component) throws Exception {
        throw new Exception();
    }

    /**
     * 删除节点，组合节点使用
     * @param component
     * @throws Exception
     */
    public void remove(Component component) throws Exception {
        throw new Exception();
    }

    /**
     * 迭代器
     * @return
     * @throws Exception
     */
    public Iterator createIterator() throws Exception {
        throw new Exception();
    }
}
