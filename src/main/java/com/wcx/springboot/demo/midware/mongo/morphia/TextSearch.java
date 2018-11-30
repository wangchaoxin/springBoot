package com.wcx.springboot.demo.midware.mongo.morphia;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.utils.IndexType;

import java.util.List;

/**
 * Morphia also supports MongoDB’s text search capabilities. In order to execute a text search against a collection,
 * the collection must have a text index defined first. Using Morphia that definition would look like this:
 * <p>
 * The $** value tells MongoDB to create a text index on all the text fields in a document.
 * A more targeted index can be created, if desired, by explicitly listing which fields to index.
 * Once the index is defined, we can start querying against it like this test does:
 */
@Indexes(@Index(fields = @Field(value = "$**", type = IndexType.TEXT)))
public class TextSearch {
    @Id
    private ObjectId id;
    private String value;
    private String language;
    private String secondLanguage;

    public TextSearch(String language, String secondLanguage) {
        this.language = language;
        this.secondLanguage = secondLanguage;
    }

    /**
     * The $** value tells MongoDB to create a text index on all the text fields in a document.
     * A more targeted index can be created, if desired, by explicitly listing which fields to index.
     * Once the index is defined, we can start querying against it like this test does:
     * @param args
     */
    public static void main(String[] args) {
        //As you can see here, we create Greeting objects for multiple languages. In our test query,
        // we’re looking for occurrences of the word “good” in any document. We created four such documents
        // and our query returns exactly those four.
        Datastore datastore = new Crud().getDatastore();
        Morphia morphia = new Morphia();
        morphia.map(TextSearch.class);
        datastore.ensureIndexes();

        datastore.save(new TextSearch("good morning", "english"),
                new TextSearch("good afternoon", "english"),
                new TextSearch("good night", "english"),
                new TextSearch("good riddance", "english"),
                new TextSearch("guten Morgen", "german"),
                new TextSearch("guten Tag", "german"),
                new TextSearch("gute Nacht", "german"));

        List<TextSearch> good = datastore.createQuery(TextSearch.class)
                .search("good")
                .order("_id")
                .asList();
        Assert.assertEquals(4, good.size());
    }
}