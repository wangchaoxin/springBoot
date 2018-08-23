package com.wcx.springboot.demo.midware.mongo.jpa;

public interface UserDao {
    void saveUser(User user);
    User findUserByUserName(String name);
    void updateUser(User user);
    void deleteUserById(long id);
}
