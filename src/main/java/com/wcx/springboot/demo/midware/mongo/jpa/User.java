package com.wcx.springboot.demo.midware.mongo.jpa;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Document用于类，以表示这个类需要映射到数据库，您也可以指定映射到数据库的集合名称
 */
@Document(collection = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -4958847041560650937L;

    /**
     * id必须是string的,mongo自动生成
     */
    @Id
    private String id;

    @Field("userName")
    @Indexed(name = "idx_name", direction = IndexDirection.DESCENDING)
    @TextIndexed
    private String userName;

    private String passWord;

    /*默认情况下，所有私有字段都映射到文档，此注解将会去除此字段的映射*/
    @Transient
    private int testField;

    private int status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getTestField() {
        return testField;
    }

    public void setTestField(int testField) {
        this.testField = testField;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", testField=" + testField +
                ", status=" + status +
                '}';
    }
}
