package com.wcx.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/test")
    public String test() {
        return "hello world";
    }
    //redirect
    @RequestMapping("/redirect")
    public String redirect() {
        return "redirect:/test";
    }
}
