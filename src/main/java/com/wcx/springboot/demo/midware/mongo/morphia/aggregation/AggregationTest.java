//package com.wcx.springboot.demo.midware.mongo.morphia.aggregation;
//
//import org.mongodb.morphia.Datastore;
//
//import java.awt.print.Book;
//import java.util.Iterator;
//
//public class AggregationTest {
//
//    public static void main(String[] args) {
//        private Datastore datastore=null;
//        Iterator<Author> aggregate = datastore.createAggregation(Book.class).group()
//                .group("author", grouping("books", push("title")))
//                .out(Author.class, options);
//    }
//}
