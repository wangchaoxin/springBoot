package com.wcx.springboot.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class TestController {
    @RequestMapping("/test")
    String test() {
        return "hello world";
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
