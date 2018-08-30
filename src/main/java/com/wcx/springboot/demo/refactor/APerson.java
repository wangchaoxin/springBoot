package com.wcx.springboot.demo.refactor;

public class APerson extends Person {
    @Override
    public int getType() {
        return Person.A;
    }
}
