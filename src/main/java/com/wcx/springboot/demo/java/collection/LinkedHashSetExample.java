package com.wcx.springboot.demo.java.collection;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * A LinkedHashSet is an ordered version of HashSet that maintains a doubly-linked List across all elements.
 * When the iteration order is needed to be maintained this class is used. When iterating through a HashSet the order
 * is unpredictable, while a LinkedHashSet lets us iterate through the elements in the order in which they were inserted.
 * When cycling through LinkedHashSet using an iterator,
 * the elements will be returned in the order in which they were inserted.
 */
public class LinkedHashSetExample {
    public static void main(String[] args) {

        linkedHashSetTest();
        LinkedHashMapTest();

    }

    private static void LinkedHashMapTest() {
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("b", "b");
        linkedHashMap.put("a", "a");
        linkedHashMap.put("c", "c");
        Set<Map.Entry<String, String>> entries = linkedHashMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());
        }

    }

    private static void linkedHashSetTest() {
        LinkedHashSet<String> linkedset =
                new LinkedHashSet<String>();

        // Adding element to LinkedHashSet
        linkedset.add("A");
        linkedset.add("B");
        linkedset.add("C");
        linkedset.add("D");

        // This will not add new element as A already exists
        linkedset.add("A");
        linkedset.add("E");

        System.out.println("Size of LinkedHashSet = " +
                linkedset.size());
        System.out.println("Original LinkedHashSet:" + linkedset);
        System.out.println("Removing D from LinkedHashSet: " +
                linkedset.remove("D"));
        System.out.println("Trying to Remove Z which is not " +
                "present: " + linkedset.remove("Z"));
        System.out.println("Checking if A is present=" +
                linkedset.contains("A"));
        System.out.println("Updated LinkedHashSet: " + linkedset);
    }
}
