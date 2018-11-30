package com.wcx.springboot.demo.midware.mongo.morphia.provider;

import org.mongodb.morphia.Datastore;

public interface MongoProvider {
    Datastore getDatastore(String databaseName);
}
