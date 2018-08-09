package com.wcx.springboot.demo.pattern.observer.normal;

public abstract class Data {
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
