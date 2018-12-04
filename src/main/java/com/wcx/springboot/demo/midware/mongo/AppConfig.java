package com.wcx.springboot.demo.midware.mongo;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * 注入mongoClien和MongoDbFactory
 */
@Configuration
public class AppConfig {

    @Value("${mongo.host}")
    private String host;

    @Value("${mongo.port}")
    private String port;

    /*
     * Use the standard Mongo driver API to create a com.mongodb.MongoClient instance.
     */
    public @Bean
    MongoClient mongoClient() {
        return new MongoClient(host);
    }

    /**
     * Registering a MongoDbFactory Instance by Using Java-based Metadata
     * @return
     */
    public @Bean
    MongoDbFactory mongoDbFactory() {
        return new SimpleMongoDbFactory(new MongoClient(host), "testSoftReference");
    }

    /**
     * mongo mongo_template
     * @return
     */
    public @Bean MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "testSoftReference");
    }

    public static void main(String[] args) {
        MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient("192.168.64.2"), "testSoftReference"));
    }
}
