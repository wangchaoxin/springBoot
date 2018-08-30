package com.wcx.springboot.demo.refactor;

public class PersonFactory {
    public static Person createPerson(int type) throws Exception {
        switch (type) {
            case Person.A:
                return new APerson();
            case Person.B:
                return new BPerson();
            default:
                throw new Exception();
        }
    }
}
