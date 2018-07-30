package com.wcx.springboot.demo.midware.mongo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.List;

/*index指明索引*/
@Entity("employees")
@Indexes(
        @Index(value = "salary", fields = @Field("salary"))
)
class Employee {
    /**
     * 每个Entity必须有id，指明主键,对应document中的_id列
     */
    @Id
    private ObjectId id;
    private String name;
    @Reference
    private Employee manager;
    @Reference     /*指明引用其他类型，必须在数据库中已经存在并且有主键*/
    private List<Employee> directReports;
    @Property("wage")     /*可以省略，目的是指明属性名称*/
    private Double salary;

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
