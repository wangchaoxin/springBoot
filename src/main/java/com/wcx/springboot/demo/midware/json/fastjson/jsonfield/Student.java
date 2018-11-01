package com.wcx.springboot.demo.midware.json.fastjson.jsonfield;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Student {
    //序列化字段名称
    @JSONField(name="ID")
    private int id;

    //指定字段不序列化
    @JSONField(serialize=false)
    //是否反序列化
    //@JSONField(deserialize=false)
    private String name;

    private String sex;
    @JSONField(serializeUsing = ModelValueSericalizer.class)
    private int value;

    //日期序列化
    @JSONField(format = "yyyyMMdd")
    private Date date=new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
