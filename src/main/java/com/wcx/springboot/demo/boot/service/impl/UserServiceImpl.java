package com.wcx.springboot.demo.boot.service.impl;

import com.wcx.springboot.demo.boot.model.User;
import com.wcx.springboot.demo.boot.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Component, @Service, @Repository, @Controller可以声明bean
 * autired 注入bean
 */
public class UserServiceImpl implements UserService, ApplicationContextAware {

    /*实现ApplicationContextAware，注入applicationContext*/
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext = applicationContext;
    }

    @Override
    public User getUser(long id) {
        return new User();
    }

    public void init() {

    }
    public void destroy() {

    }


}
