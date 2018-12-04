package com.wcx.springboot.demo.midware.mongo.morphia;

        import com.mongodb.MongoClient;
        import com.mongodb.WriteResult;
        import com.wcx.springboot.demo.midware.mongo.morphia.service.VideoFile;
        import org.bson.types.ObjectId;
        import org.mongodb.morphia.Datastore;
        import org.mongodb.morphia.Morphia;
        import org.mongodb.morphia.query.FindOptions;
        import org.mongodb.morphia.query.Query;
        import org.mongodb.morphia.query.UpdateOperations;
        import org.mongodb.morphia.query.UpdateResults;

        import java.util.ArrayList;
        import java.util.List;

public class Crud {
    private Datastore datastore;

    public Datastore getDatastore() {
        final Morphia morphia = new Morphia();

        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        morphia.mapPackage("org.mongodb.morphia.example");

        // create the Datastore connecting to the default port on the local host
        final Datastore datastore = morphia.createDatastore(new MongoClient("192.168.64.2", 27017), "testSoftReference");
        datastore.ensureIndexes();
        this.datastore = datastore;
        return datastore;
    }

    /**
     * save
     */
    public void save() {
        //1.simple save
        final Employee elmer = new Employee("Elmer Fudd", 50000.0);
        datastore.save(elmer);

        //2. define some relationships and save those
        final Employee daffy = new Employee("Daffy Duck", 40000.0);
        datastore.save(daffy);

        final Employee pepe = new Employee("Pepé Le Pew", 25000.0);
        datastore.save(pepe);

        elmer.getDirectReports().add(daffy);
        elmer.getDirectReports().add(pepe);

        datastore.save(elmer);
    }

    /**
     * query
     */
    public void query() {
        //查询全部
        final Query<Employee> query = datastore.createQuery(Employee.class);
        List<Employee> employees = query.asList();

        //1.field查询
        // the field() method here is used to filter on the named field and returns an instance of
        // an interface with a number of methods to build a query
        List<Employee> salary = datastore.createQuery(Employee.class)
                .field("salary").lessThanOrEq(30000)
                .asList();
        //2.filer查询
        // The other approach uses the filter() method which is a little more free form and succinct than field(). Here we can embed certain operators in the query string. While this is less verbose than the alternative,
        // it does leave more things in the string to validate and potentially get wrong:
        List<Employee> underpaid = datastore.createQuery(Employee.class)
                .filter("salary <=", 30000)
                .filter("salary in", new ArrayList<>())
                .asList();
        //filter 符号  ==   !=  >  >=  <  <=  exists  in  nin


        //3.and查询
        final Query<Employee> q = datastore.createQuery(Employee.class);
        q.and(
                q.criteria("salary").greaterThan(10),
                q.criteria("salary").lessThan(100)
        );
        // or 查询
        q.or(
                q.criteria("salary").greaterThan(10),
                q.criteria("salary").lessThan(100)
        );

        //4. 排序order by
        //The javadoc has complete examples but this String consists of a list of comma delimited fields to order by.
        // To reverse the sort order for a particular field simply prefix that field with a -
        q.order("age,-income");

        //5.分页查询
        //默认就是and 查询,分页查询 limit and  skip
        datastore.createQuery(Employee.class)
                .field("salary").lessThan(5)
                .field("salary").greaterThan(4)
                .field("salary").greaterThan(10).asList(new FindOptions().skip(1).limit(1));
        //6.查询个数

        //查询个数
        datastore.getCount(Employee.class);
        datastore.getCount(q);
    }


    /**
     * update
     */
    public void update() {

        /*update**/
        //1 先查询处理
        //These updates take two components: a query, and a set of update operations
        final Query<Employee> underPaidQuery = datastore.createQuery(Employee.class)
                .filter("salary <=", 30000);
        //2 To define how we want to update the documents matched by this query, we create an UpdateOperations instance:
        final UpdateOperations<Employee> updateOperations = datastore.createUpdateOperations(Employee.class)
                .inc("salary", 10000);
        //3 更新 This line executes the update in the database without having to pull in however many documents are matched by the query.
        // The UpdateResults instance returned will contain various statistics about the update operation.
        final UpdateResults results = datastore.update(underPaidQuery, updateOperations);
    }

    /**
     * remove
     */
    public void remove() {
        //1.remove
        // Removing just needs a query to find and delete the documents in question and then tell the Datastore to delete them
        final Query<Employee> overPaidQuery = datastore.createQuery(Employee.class)
                .filter("salary >", 100000);
        datastore.delete(overPaidQuery);
        //2.根据Id删除
        WriteResult delete = datastore.delete(VideoFile.class, new ObjectId("11"));
    }


    public static void main(String[] args) {

    }


}
