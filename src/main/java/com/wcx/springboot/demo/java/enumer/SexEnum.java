package com.wcx.springboot.demo.java.enumer;

public enum SexEnum {

    MALE("男"), FEMALE("女");
    private String desc;

    SexEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
