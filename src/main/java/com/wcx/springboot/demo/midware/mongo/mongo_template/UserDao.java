package com.wcx.springboot.demo.midware.mongo.mongo_template;

import com.wcx.springboot.demo.midware.mongo.jpa.User;

public interface UserDao {

    void saveUser(User user);

    User findUserByUserName(String name);

    void updateUser(User user);

    void deleteUserById(long id);
}
