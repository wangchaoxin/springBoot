package com.wcx.springboot.demo.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 将属性文件注入进实体中
 */
@Component
@ConfigurationProperties(prefix = "my.test")
public class ProfileConfigure {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
