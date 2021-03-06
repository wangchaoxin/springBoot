package com.wcx.springboot.demo.boot.controller;

import com.wcx.springboot.demo.boot.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("hello")
public class HelloController {
    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String readersBooks(
            Model model) {
        model.addAttribute("name", "wangchaoxin");
        User user = new User(2, "wang");
        model.addAttribute("user", user);
        return "index";   //对应templates里的index.html
    }
}
