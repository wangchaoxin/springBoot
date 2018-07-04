package com.wcx.springboot.demo.service.impl;

import com.wcx.springboot.demo.model.User;
import com.wcx.springboot.demo.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User getUser(long id) {
        return new User();
    }
}
