package com.wcx.springboot.demo.midware.json.fastjson.readfromfile;

import java.util.HashMap;
import java.util.Map;

public class VO {
    private int id;
    private Map<String, Object> attributes = new HashMap<String, Object>();


    public VO(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "VO{" +
                "id=" + id +
                ", attributes=" + attributes +
                '}';
    }
}
