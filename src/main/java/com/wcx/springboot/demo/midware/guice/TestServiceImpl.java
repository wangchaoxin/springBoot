package com.wcx.springboot.demo.midware.guice;

public class TestServiceImpl implements TestService {
    private String name;
    @Override
    public void print() {
        System.out.println("TestService.print");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
