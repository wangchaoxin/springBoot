package com.wcx.springboot.demo.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
/**
 * PropertySource设置从另一个文件中加载配置
 */
@Component
@PropertySource("classpath:/config/redis.properties")
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
