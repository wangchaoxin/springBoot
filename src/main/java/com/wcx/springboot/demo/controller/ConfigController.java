package com.wcx.springboot.demo.controller;

import com.wcx.springboot.demo.configure.Configuration;
import com.wcx.springboot.demo.configure.ProfileConfig;
import com.wcx.springboot.demo.configure.RedisConfig;
import com.wcx.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("config")
public class ConfigController {
    @Autowired
    private Configuration configuration;
    @Autowired
    private UserService userService;
    @Autowired
    private ProfileConfig profileConfig;
    @Autowired
    private RedisConfig redisConfig;

    @RequestMapping("/get")
    String test() {
        String configName = configuration.getName();
        String name= profileConfig.getName();
        String redisName = redisConfig.getName();
        return redisName;
    }

}
