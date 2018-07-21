package com.wcx.springboot.demo.controller;

import com.wcx.springboot.demo.configure.Configuration;
import com.wcx.springboot.demo.configure.ProfileConfigure;
import com.wcx.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class ConfigController {
    @Autowired
    private Configuration configuration;
    @Autowired
    private UserService userService;
    @Autowired
    private ProfileConfigure profileConfigure;

    @RequestMapping("/test")
    String test() {
//        User user = userService.getUser(1);
//        return "get resource name:" + configuration.getName();
        String configName = configuration.getName();
        String name= profileConfigure.getName();
        return name;
    }

}
