package com.wcx.springboot.demo.refactor;

public class BPerson extends Person {
    @Override
    public int getType() {
        return Person.B;
    }
}
