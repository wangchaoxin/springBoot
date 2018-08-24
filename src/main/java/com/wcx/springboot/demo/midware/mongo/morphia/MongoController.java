package com.wcx.springboot.demo.midware.mongo.morphia;

import org.mongodb.morphia.Key;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mongo")
public class MongoController {


    @RequestMapping("save")
    public void save() {
        MongoProvider mongoProvider = new MongoProvider("test-mongo");
        final Employee elmer = new Employee("Elmer Fudd", 50000.0);
        Key<Employee> employeeKey = mongoProvider.getDatastore().save(elmer);
        System.out.println("mongo save success");
    }
}
