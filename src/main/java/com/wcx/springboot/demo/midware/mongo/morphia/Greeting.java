package com.wcx.springboot.demo.midware.mongo.morphia;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.utils.IndexType;

@Indexes(@Index(fields = @Field(value = "$**", type = IndexType.TEXT)))
public class Greeting {
    @Id
    private ObjectId id;
    private String value;
    private String language;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}