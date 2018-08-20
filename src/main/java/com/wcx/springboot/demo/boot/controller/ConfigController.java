package com.wcx.springboot.demo.boot.controller;

import com.wcx.springboot.demo.boot.config.Config;
import com.wcx.springboot.demo.boot.config.ProfileConfig;
import com.wcx.springboot.demo.boot.config.RedisConfig;
import com.wcx.springboot.demo.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@EnableAutoConfiguration
@RequestMapping("config")
public class ConfigController {
    @Autowired
    private Config configuration;
    @Autowired
    private UserService userService;
    @Autowired
    private ProfileConfig profileConfig;
    /*resource 自动注入*/
    @Resource
    private RedisConfig redisConfig;

    @RequestMapping("/get")
    String test() {
        String configName = configuration.getName();
        String name= profileConfig.getName();
        String redisName = redisConfig.getName();
        return redisName;
    }

}
