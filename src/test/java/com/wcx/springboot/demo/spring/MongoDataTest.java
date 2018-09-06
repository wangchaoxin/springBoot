package com.wcx.springboot.demo.spring;

import com.wcx.springboot.demo.midware.mongo.jpa.User;
import com.wcx.springboot.demo.midware.mongo.mongo_template.UserDao;
import com.wcx.springboot.demo.midware.mongo.jpa.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDataTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() throws Exception {
        User user = new User();
        user.setUserName("小明");
        user.setPassWord("fffooo123");
        user.setStatus(1);
        user.setTestField(2);
        userDao.saveUser(user);
    }

    @Test
    public void findUserByUserName() {
        User user = userDao.findUserByUserName("小明");
        System.out.println("user is " + user);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setUserName("天空");
        user.setPassWord("fffxxxx");
        userDao.updateUser(user);
    }

    @Test
    public void deleteUserById() {
        userDao.deleteUserById(1l);
    }

    /**
     * repository test
     */
    @Test
    public void testFindRepository() {
        //find
        List<User> users = userRepository.findAll();
        User userById = userRepository.findById(2L).get();
        long count = userRepository.count();

        //page
        Sort sort = new Sort(Sort.Direction.ASC, "passWord");
        Pageable pageable = new PageRequest(0, 2, sort);
        Page<User> pageUsers = userRepository.findAll(pageable);
        Page<User> pageUsers1 = userRepository.findByIdAndStatusIn("5b7e5ffd77b1e40e58537c34", Arrays.asList(3, 4), null);
    }

    /**
     * 设置id如果相同的话更新该条记录
     */
    @Test
    public void testSaveRepository() {
//        String id= "5b7e5ffd77b1e40e58537c34";
        User user = new User();
//        user.setId(id);
        user.setUserName("哈哈11");
        user.setPassWord("111");
        user.setStatus(1);
        user.setTestField(2);
        User save = userRepository.save(user);
        System.out.println(save);
        assert (user.getPassWord().equals("111"));
    }
}
