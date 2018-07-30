package com.wcx.springboot.demo.midware.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisProvider {

    private StringRedisTemplate template;

    @Autowired
    public RedisProvider(StringRedisTemplate template) {
        this.template = template;
    }

    public StringRedisTemplate getTemplate() {
        return template;
    }
}