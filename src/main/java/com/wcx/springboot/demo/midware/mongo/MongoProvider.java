package com.wcx.springboot.demo.midware.mongo;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.wcx.springboot.demo.boot.config.MongoConfig;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.stereotype.Component;

@Component
public class MongoProvider {

//    @Autowired
    private MongoConfig mongoConfig;

    private Datastore datastore;

    public MongoProvider(String databaseName) {
        final Morphia morphia = new Morphia();

        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        // 在该目录下找到有@Entity的映射类
        morphia.mapPackage("com.wcx.springboot.demo.midware.mongo");

        // create the Datastore connecting to the default PORT on the local HOST
        // connect to multiple databases by creating different Datastore instances
        ServerAddress serverAddress = new ServerAddress(mongoConfig.getHost(), mongoConfig.getPort());
        final Datastore datastore = morphia.createDatastore(new MongoClient(serverAddress), databaseName);
        datastore.ensureIndexes();
        this.datastore=datastore;
    }

    public MongoProvider() {

    }
    public Datastore getDatastore() {
        return datastore;
    }
}
