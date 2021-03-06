package com.wcx.springboot.demo.boot.controller;

import com.wcx.springboot.demo.boot.model.User;
import com.wcx.springboot.demo.boot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //返回Json格式数据
@EnableAutoConfiguration   //该注解会自动引入一些包,如果依赖web-starter,会自动引入tomcat
public class RestfulController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    User test() {
        User user = userService.getUser(1);
        user.setId(1);
        user.setName("wang");
        logger.info("/user");
        return user;
    }
}
