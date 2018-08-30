package com.wcx.springboot.demo.midware.mongo.morphia;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import java.util.List;

public class Client {
    public static void main(String[] args) {
        final Morphia morphia = new Morphia();

        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        morphia.mapPackage("org.mongodb.morphia.example");

        // create the Datastore connecting to the default port on the local host
        final Datastore datastore = morphia.createDatastore(new MongoClient("192.168.64.2", 27017), "test");
        datastore.ensureIndexes();


       /* final Employee elmer = new Employee("Elmer Fudd", 50000.0);
        datastore.save(elmer);*/

        /*查询全部**/
        final Query<Employee> query = datastore.createQuery(Employee.class);
        List<Employee> employees = query.asList();

        /*条件查询**/
        employees = datastore.createQuery(Employee.class)
                .field("salary").lessThanOrEq(30000)
                .asList();
        /*filter()查询**/
        List<Employee> underpaid = datastore.createQuery(Employee.class)
                .filter("salary <=", 30000)
                .asList();

        /*update**/
        //1 先查询处理
        final Query<Employee> underPaidQuery = datastore.createQuery(Employee.class)
                .filter("salary <=", 30000);
        //2 To define how we want to update the documents matched by this query, we create an UpdateOperations instance:
        final UpdateOperations<Employee> updateOperations = datastore.createUpdateOperations(Employee.class)
                .inc("salary", 10000);
        //3 更新
        final UpdateResults results = datastore.update(underPaidQuery, updateOperations);

        /*remove**/
        // Removing just needs a query to find and delete the documents in question and then tell the Datastore to delete them
        final Query<Employee> overPaidQuery = datastore.createQuery(Employee.class)
                .filter("salary >", 100000);
        datastore.delete(overPaidQuery);


        /*复杂查询**/
        final Query<Employee> q = datastore.createQuery(Employee.class);
        //and 查询
        q.and(
                q.criteria("salary").greaterThan(10),
                q.criteria("salary").lessThan(100)
        );
        //默认就是and 查询
        datastore.createQuery(Employee.class)
                .field("salary").lessThan(5)
                .field("salary").greaterThan(4)
                .field("salary").greaterThan(10);


        System.out.println("end");

    }
}
