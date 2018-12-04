/*
package com.wcx.springboot.demo.midware.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import static java.util.Collections.singletonList;

*/
/**
 * 用户名密码登录
 *//*

public class ApplicationContextEventTestsAppConfig extends AbstractMongoConfiguration {
    */
/**
     * 用户名密码有特殊字符需要urlEncode
     * @return
     *//*

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(singletonList(new ServerAddress("127.0.0.1", 27017)),
                singletonList(MongoCredential.createCredential("name", "db", "pwd".toCharArray())));
    }

    @Override
    protected String getDatabaseName() {
        return "testSoftReference";
    }
}
*/
