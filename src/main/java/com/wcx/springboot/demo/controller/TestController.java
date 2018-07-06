package com.wcx.springboot.demo.controller;

import com.wcx.springboot.demo.configure.Configuration;
import com.wcx.springboot.demo.configure.ProfileConfigure;
import com.wcx.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class TestController {
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
        String name= profileConfigure.getName();
        return name;
    }

    //redirect
    @RequestMapping("/redirect")
    String redirect() {
        return "redirect:/test";
    }

    public static void main(String[] args) {
        SpringApplication.run(TestController.class, args);
    }
}
