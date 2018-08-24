package com.wcx.springboot.demo.midware.mongo.mongo_template;

import com.mongodb.MongoClient;
import com.wcx.springboot.demo.boot.config.MongoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

public class MongoApp {
    private static Logger log = LoggerFactory.getLogger(MongoApp.class);

    @Autowired
    MongoTemplate mongoTemplate = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "test"));
    @Autowired
    private static MongoConfig mongoConfig;

    public static void main(String[] args) {

        MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient("192.168.64.2"), "test"));

        Person p = new Person("Joe", 34);

        //insert 和save区别：如果数据库里有相同id，insert抛出异常，save覆盖原数据
        //insert(collection) 同时插入多条数据
        // Insert is used to initially store the object into the database.
        mongoOps.insert(p);
        // insert to the specified collection name
//        mongoOps.insert(p,"person");
        log.info("Insert: " + p);

        // Find
        p = mongoOps.findById(p.getId(), Person.class);
        log.info("Found: " + p);

        // Update
        mongoOps.updateFirst(query(where("name").is("Joe")), update("age", 35), Person.class);
        p = mongoOps.findOne(query(where("name").is("Joe")), Person.class);
        log.info("Updated: " + p);

        // Delete
        mongoOps.remove(p);

        // Check that deletion worked
        List<Person> people = mongoOps.findAll(Person.class);
        log.info("Number of people = : " + people.size());

        //upsert
        mongoOps.upsert(query(where("ssn").is(1111).and("firstName").is("Joe").and("Fraizer").is("Update")), update("name", "hha"), Person.class);

        mongoOps.dropCollection(Person.class);

    }

    /**
     * The findAndModify(…) method on MongoCollection can update a document and
     * return either the old or newly updated document in a single operation.
     * MongoTemplate provides four findAndModify overloaded methods that take Query
     * and Update classes and converts from Document to your POJOs:
     */
    public static void testFindAndModify(MongoOperations mongoTemplate) {
        mongoTemplate.insert(new Person("Tom", 21));
        mongoTemplate.insert(new Person("Dick", 22));
        mongoTemplate.insert(new Person("Harry", 23));

        Query query = new Query(Criteria.where("firstName").is("Harry"));
        Update update = new Update().inc("age", 1);
        Person p = mongoTemplate.findAndModify(query, update, Person.class); // return's old person object

        p = mongoTemplate.findOne(query, Person.class);

//      //Now return the newly updated document when updating
        p = mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Person.class);

    }
}

