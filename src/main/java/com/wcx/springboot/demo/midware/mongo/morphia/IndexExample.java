package com.wcx.springboot.demo.midware.mongo.morphia;


import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexType;

/**
 * Class Level Indexes
 * Class level indexing begins with the @Indexes annotation. This is a container annotation whose sole
 * purpose is to hold a number of @Index annotations. This annotation has two primary components to cover
 * here: fields and options. An index definition would take the following form:
 */
@Entity
@Indexes({
        @Index(fields = {@Field(value = "field1", type = IndexType.DESC), @Field(value = "field2", weight = 1)}, options = @IndexOptions(unique = true)),
        @Index(fields = @Field(value = "field3", type = IndexType.ASC))
})
public class IndexExample {
    //index 属性
   /*  1.type()
    Default: IndexType.ASC
    Indicates the “type” of the index (ascending, descending, geo2D, geo2d sphere, or text) to create on the field.*/
    //2.weight():Specifies the weight to use when creating a text index. This value only makes sense when direction is IndexType.TEXT.
    /* 3.background()
    Default: false
    This value determines if the index build is a blocking call or not. By default, creating an index blocks all
    other operations on a database. When building an index on a collection, the database that holds the collection
    is unavailable for read or write operations until the index build completes. For potentially long running index
    building operations, consider the background operation so that the MongoDB database remains available during the
    index building operation. The MongoDB manual has more detail.*/
   /* 4.unique()
    Default: false
    Creates a unique index so that the collection will not accept insertion of documents where the index key or keys match an existing value in the index. Specify true to create a unique index.*/
    @Id
    private ObjectId id;

    //5.field level index
    /*Field level indexing is a simpler approach to defining a basic, single key index.
    These indexes are defined by applying the @Indexed annotation to a particular field on a class.
    Because the index definition is applied at the field level, the index is created using only that field
    and so the @Field annotations are unnecessary. The options for the index are the same as defined above.
     A field level index definition would look like this:*/
    @Indexed(options = @IndexOptions(unique = true))
    private String field;


    @Property
    private String field2;
    @Property("f3")
    private String field3;
}


