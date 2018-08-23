package com.wcx.springboot.demo.pattern.left.builder;

public class Client {
    public static void main(String[] args) {
        AbstractBuilder abstractBuilder=new ConcreteBuilder();
        Product prodcut = abstractBuilder.buildPart().getProdcut();
        System.out.println(prodcut);
    }
}
