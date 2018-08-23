package com.wcx.springboot.demo.pattern.left.builder;

public abstract class AbstractBuilder {

    /**
     * 构建部件
     */
    public abstract AbstractBuilder buildPart();

    /**
     * 获取产品
     * @return
     */
    public abstract Product getProdcut();
}
