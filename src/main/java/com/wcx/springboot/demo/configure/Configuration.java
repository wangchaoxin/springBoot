package com.wcx.springboot.demo.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Configuration {
    @Value("${wcx.name}")
    public String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }
}
