package com.wcx.springboot.demo.pattern.left.builder;

public class ConcreteBuilder extends AbstractBuilder {

    Product product = new Product();

    @Override
    public AbstractBuilder buildPart() {
        product.setName("wang");
        return this;
    }

    @Override
    public Product getProdcut() {
        return product;
    }
}
