package com.wcx.springboot.demo.midware.mongo.morphia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.Arrays;

public class Update {

    private Datastore datastore;
    private Query<Hotel> updateQuery = datastore.createQuery(Hotel.class);

    /**
     * 1.set()/unset()
     */
    public void test1() {
        //To change the name of the hotel, one would use something like this:
        UpdateOperations<Hotel> ops = datastore
                .createUpdateOperations(Hotel.class)
                .set("name", "Fairmont Chateau Laurier");
        datastore.update(updateQuery, ops);
        //This also works for embedded documents. To change the name of the city in the address, one would use something like this:
        UpdateOperations<Hotel> ops1 = datastore
                .createUpdateOperations(Hotel.class)
                .set("address.city", "Ottawa");
        datastore.update(updateQuery, ops1);

        //Values can also be removed from documents as shown below:
        //After this update, the name of the hotel would be null when the entity is loaded.
        UpdateOperations<Hotel> ops2 = datastore
                .createUpdateOperations(Hotel.class)
                .unset("name");
        datastore.update(updateQuery, ops2);
    }

    /**
     * 2.inc()/dec()
     * To simply increment or decrement values in the database, updates like these would be used:
     */
    public void test2() {
        // increment 'stars' by 4
        UpdateOperations<Hotel> ops = datastore
                .createUpdateOperations(Hotel.class)
                .inc("stars");
        datastore.update(updateQuery, ops);

        // increment 'stars' by 4
        ops = datastore
                .createUpdateOperations(Hotel.class)
                .inc("stars", 4);
        datastore.update(updateQuery, ops);

        // decrement 'stars' by 1
        ops = datastore
                .createUpdateOperations(Hotel.class)
                .dec("stars");  // same as .inc("stars", -1)
        datastore.update(updateQuery, ops);

        // decrement 'stars' by 4
        ops = datastore
                .createUpdateOperations(Hotel.class)
                .inc("stars", -4);
        datastore.update(updateQuery, ops);
    }

    /**
     * 3.push()/addToSet()
     * push() is used to add a value to an array field:
     */
    public void test3() {
        UpdateOperations<Hotel> ops = datastore
                .createUpdateOperations(Hotel.class)
                .push("roomNumbers", 11);
        datastore.update(updateQuery, ops);

        //This will issue a $push operation adding 11 to the list. This might result in duplicated values in this field.
        // If the values should be unique, use addToSet() instead:
        ops = datastore
                .createUpdateOperations(Hotel.class)
                .addToSet("roomNumbers", 11);
        datastore.update(updateQuery, ops);

    }

    /**
     * 4.removeFirst()/removeLast()/removeAll()
     * To remove values from a list, use removeFirst(), removeLast(), or removeAll():
     */
    public void test4() {
        //given roomNumbers = [ 1, 2, 3 ]
        UpdateOperations<Hotel> ops = datastore
                .createUpdateOperations(Hotel.class)
                .removeFirst("roomNumbers");
        datastore.update(updateQuery, ops);  // [ 2, 3 ]

        //given roomNumbers = [ 1, 2, 3 ]
        ops = datastore
                .createUpdateOperations(Hotel.class)
                .removeLast("roomNumbers");
        datastore.update(updateQuery, ops);  // [ 1, 2 ]
        ops = datastore
                .createUpdateOperations(Hotel.class)
                .removeLast("roomNumbers");
        datastore.update(updateQuery, ops);  // [ 1 ]
        ops = datastore
                .createUpdateOperations(Hotel.class)
                .removeLast("roomNumbers");
        datastore.update(updateQuery, ops);  // []   empty array

        //given roomNumbers = [ 1, 2, 3, 3 ]
        ops = datastore
                .createUpdateOperations(Hotel.class)
                .removeAll("roomNumbers", 3);
        datastore.update(updateQuery, ops);  // [ 1, 2 ]

        //given roomNumbers = [ 1, 2, 3, 3 ]
        ops = datastore
                .createUpdateOperations(Hotel.class)
                .removeAll("roomNumbers", Arrays.asList(2, 3));
        datastore.update(updateQuery, ops);  // [ 1 ]
    }

    /**
     * 5.updateFirst()
     * In the default driver and shell this is the default behavior. In Morphia we feel like updating all
     * the results of the query is a better default (see below).
     */
    public void test5() {
        UpdateOperations<Hotel> ops = datastore.createUpdateOperations(Hotel.class).inc("stars", 50);

        // morphia exposes a specific updateFirst to update only the first hotel matching the query
        datastore
                .updateFirst(datastore
                                .find(Hotel.class)
                                .order("stars"),
                        ops);  // update only Last Chance
        datastore
                .updateFirst(datastore
                                .find(Hotel.class)
                                .order("-stars"),
                        ops); // update only Fairmont
    }

    /**
     * 6.Multiple Operations
     * You can also perform multiple update operations within a single update.
     */
    public void test6() {
        //set city to Ottawa and increment stars by 1
        UpdateOperations<Hotel> ops = datastore
                .createUpdateOperations(Hotel.class)
                .set("city", "Ottawa")
                .inc("stars");
        datastore.update(updateQuery, ops);

        //if you perform multiple operations in one command on the same property, results will vary
        ops = datastore
                .createUpdateOperations(Hotel.class)
                .inc("stars", 50)
                .inc("stars");  //increments by 1
        ops = datastore
                .createUpdateOperations(Hotel.class)
                .inc("stars")
                .inc("stars", 50);  //increments by 50

        //you can't apply conflicting operations to the same property
        ops = datastore
                .createUpdateOperations(Hotel.class)
                .set("stars", 1)
                .inc("stars", 50); //causes error
    }

    /**
     * 7.createIfMissing (overload parameter)
     * All of the update methods on Datastore are overloaded and accept a createIfMissing parameter
     */
    public void test7() {
        UpdateOperations<Hotel> ops = datastore
                .createUpdateOperations(Hotel.class)
                .inc("stars", 50);

        //update, if not found create it
        datastore
                .updateFirst(datastore
                                .createQuery(Hotel.class)
                                .field("stars").greaterThan(100),
                        ops, true);

        // creates { "_id" : ObjectId("4c60629d2f1200000000161d"), "stars" : 50 }
    }
}
