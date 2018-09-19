package com.wcx.springboot.demo.midware.spring;

import com.wcx.springboot.demo.midware.mongo.morphia.Employee;
import com.wcx.springboot.demo.midware.mongo.morphia.MongoProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MorphiaTest {

    @Autowired
    private MongoProvider mongoProvider;

    @Test
    public void save() {
        Datastore datastore = mongoProvider.getDatastore("test");
        final Employee elmer = new Employee("Elmer Fudd", 50000.0);
        Key<Employee> employeeKey = datastore.save(elmer);
        System.out.println("save success");
    }
}
