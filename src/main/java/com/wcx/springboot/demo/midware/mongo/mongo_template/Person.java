package com.wcx.springboot.demo.midware.mongo.mongo_template;

public class Person {
    /**
     * for auto-generation of an ObjectId to succeed,
     * the type of the Id property or field in your class must be a String, an ObjectId, or a BigInteger
     */
    private String id;
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}
