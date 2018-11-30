package com.wcx.springboot.demo.midware.mongo.morphia;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.List;

/*index指明索引*/
@Entity("employees")
//唯一索引  options = @IndexOptions(unique = true))
@Indexes({@Index(value = "salary", fields = @Field("salary")), @Index(value = "salary", fields = @Field("salary"), options = @IndexOptions(unique = true))})
public class Employee {
    /**
     * 每个Entity必须有id，指明主键,对应document中的_id列
     */
    @Id
    private ObjectId id;
    private String name;
    //This just leaves @Reference. This annotation is telling Morphia that this field refers to other Morphia mapped entities.
    // In this case Morphia will store what MongoDB calls a DBRef which is just a collection name and key value.
    // These referenced entities must already be saved or at least have an ID assigned or Morphia will throw an exception.
    @Reference
    private Employee manager;
    @Reference     /*指明引用其他类型，必须在数据库中已经存在并且有主键*/
    private List<Employee> directReports;
    @Property("wage")     /*可以省略，目的是指明属性名称*/
    private Double salary;
    @Transient  //不存储在数据库中
    private int field1;

    /* @Version marks a field in an entity to control optimistic locking. If the versions change in the database while
      modifying an entity (including deletes) a ConcurrentModificationException will be thrown. This field will
      be automatically managed for you – there is no need to set a value and you should not do so. If another name
      beside the Java field name is desired, a name can be passed to this annotation to change the document’s field name.*/
    @Version
    private int version;

    /**
     * transient 和static 属性不存到mongo
     */
    private transient int age;

    /**
     * 必须提供默认构造函数
     */
    public Employee() {
    }

    public Employee(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
