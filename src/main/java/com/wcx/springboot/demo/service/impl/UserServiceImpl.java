package com.wcx.springboot.demo.service.impl;

import com.wcx.springboot.demo.model.User;
import com.wcx.springboot.demo.service.UserService;

/**
 *  Component, @Service, @Repository, @Controller可以声明bean
 *  autired 注入bean
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(long id) {
        return new User();
    }
}
