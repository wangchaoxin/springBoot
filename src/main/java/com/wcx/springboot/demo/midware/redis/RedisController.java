package com.wcx.springboot.demo.midware.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisProvider redisProvider;

    @RequestMapping("get")
    public void redis() {
        StringRedisTemplate stringRedisTemplate = redisProvider.getTemplate();
        String key = "key-testSoftReference";
        String value = "value-testSoftReference";

        valueOperation(stringRedisTemplate, key, value);
//        hashOperation(stringRedisTemplate, key, value);
//        listOperation(stringRedisTemplate, key, value);
//        setOperation(stringRedisTemplate, key);


    }

    private void setOperation(StringRedisTemplate stringRedisTemplate, String key) {
        // ZSetOperations, BoundZSetOperations
        stringRedisTemplate.opsForZSet().add(key, "player1", 12.0d);
        stringRedisTemplate.opsForZSet().add(key, "player2", 11.0d);
        stringRedisTemplate.boundZSetOps(key).add("player1", 12.0d);
        stringRedisTemplate.boundZSetOps(key).add("player2", 11.0d);
    }

    private void listOperation(StringRedisTemplate stringRedisTemplate, String key, String value) {
        // ListOperations, BoundListOperations
        stringRedisTemplate.opsForList().leftPush(key, value);
        stringRedisTemplate.opsForList().rightPush(key, value);
        stringRedisTemplate.opsForList().rightPop(key, 1, TimeUnit.SECONDS);
        stringRedisTemplate.opsForList().leftPop(key, 1, TimeUnit.SECONDS);
        stringRedisTemplate.boundListOps(key).leftPush(value);
        stringRedisTemplate.boundListOps(key).rightPush(value);
        stringRedisTemplate.boundListOps(key).rightPop(1, TimeUnit.SECONDS);
        stringRedisTemplate.boundListOps(key).leftPop(1, TimeUnit.SECONDS);
    }

    private void hashOperation(StringRedisTemplate stringRedisTemplate, String key, String value) {
        // HashOperations, BoundHashOperations
        stringRedisTemplate.opsForHash().put(key, "hashKey", value);
        stringRedisTemplate.boundHashOps(key).put("hashKey", value);
    }

    private void valueOperation(StringRedisTemplate stringRedisTemplate, String key, String value) {
        // ValueOperations, BoundValueOperations
        stringRedisTemplate.opsForValue().set(key, value);
        System.out.println("redis value:"+stringRedisTemplate.opsForValue().get(key));
        stringRedisTemplate.boundValueOps(key).set(value);
    }
}
