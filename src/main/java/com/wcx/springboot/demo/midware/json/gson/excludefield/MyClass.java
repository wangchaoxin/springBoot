package com.wcx.springboot.demo.midware.json.gson.excludefield;

import com.google.gson.annotations.Expose;

public class MyClass {
    private long id;
    @Expose
    private String name;
    @Exclude
    private String other;
    private MySubClass subclass;

    public MyClass(long id, String name, String other, MySubClass subclass) {
        this.id = id;
        this.name = name;
        this.other = other;
        this.subclass = subclass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public MySubClass getSubclass() {
        return subclass;
    }

    public void setSubclass(MySubClass subclass) {
        this.subclass = subclass;
    }
}