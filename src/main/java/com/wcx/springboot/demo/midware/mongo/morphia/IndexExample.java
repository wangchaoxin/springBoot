package com.wcx.springboot.demo.midware.mongo.morphia;


import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexType;

/**
 * 索引
 */
@Entity
@Indexes({
        //weight:权重，text索引才有用
        @Index(fields = {@Field(value = "field1", type = IndexType.DESC),@Field(value = "field2",weight = 1)}),
        @Index(fields = @Field(value = "field3", type = IndexType.ASC))
})
public class IndexExample {
    @Id
    private ObjectId id;
    //field level index
    @Indexed(options = @IndexOptions(unique = true))
    private String field;
    @Property
    private String field2;
    @Property("f3")
    private String field3;
}


