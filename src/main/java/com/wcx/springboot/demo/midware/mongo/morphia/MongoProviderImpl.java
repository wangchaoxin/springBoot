package com.wcx.springboot.demo.midware.mongo.morphia;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.wcx.springboot.demo.boot.config.MongoConfig;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MongoProviderImpl implements MongoProvider {

    @Autowired
    private MongoConfig mongoConfig;

    private Datastore datastore;

    private Morphia morphia;

    private ServerAddress serverAddress;

    public MongoProviderImpl() {

    }

    /**
     * 传入不同的databaseName，获取不同datastore的instance
     *
     * @param databaseName
     */
    @Override
    public Datastore getDatastore(String databaseName) {
        // create the Datastore connecting to the default PORT on the local HOST
        // connect to multiple databases by creating different Datastore instances
        final Datastore datastore = morphia.createDatastore(new MongoClient(serverAddress), databaseName);
        datastore.ensureIndexes();
        this.datastore = datastore;
        return datastore;
    }

    /**
     * 确保mongoConfig已经初始化完成
     */
    @PostConstruct
    void init(){
        this.morphia = new Morphia();
        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        // 在该目录下找到有@Entity的映射类
        morphia.mapPackage("com.wcx.springboot.demo.midware.mongo");
        this.serverAddress = new ServerAddress(mongoConfig.getHost(), mongoConfig.getPort());
    }

}
