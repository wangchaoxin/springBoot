package com.wcx.springboot.demo;

import com.wcx.springboot.demo.midware.mongo.jpa.MongoBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {


    @Autowired
    private MongoBean mongoBean;

    @Test
    public void testMongo() {
        System.out.println("test mongo");
    }

}
