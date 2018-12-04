package com.wcx.springboot.demo.midware.mongo.morphia;

import com.wcx.springboot.demo.midware.mongo.morphia.provider.MongoProvider;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mongo")
public class MongoController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MongoProvider mongoProvider;

    @RequestMapping
    public void save() {
        Datastore datastore = mongoProvider.getDatastore("testSoftReference-mongo");
        final Employee elmer = new Employee("Elmer Fudd", 50000.0);
        Key<Employee> employeeKey = datastore.save(elmer);
        logger.info("save success");
    }
}
