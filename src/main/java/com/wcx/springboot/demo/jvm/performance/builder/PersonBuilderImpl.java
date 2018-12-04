package com.wcx.springboot.demo.jvm.performance.builder;

public class PersonBuilderImpl extends PersonBuilder{

    private Person person = new Person();
    @Override
    public PersonBuilder buildName(String name) {
        person.setName(name);
        return this;
    }

    @Override
    public PersonBuilder builAge(int age) {
        person.setAge(age);
        return this;
    }

    @Override
    public Person build() {
        return person;
    }
}
