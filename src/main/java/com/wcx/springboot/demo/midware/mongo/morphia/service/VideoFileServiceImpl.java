package com.wcx.springboot.demo.midware.mongo.morphia.service;

import com.google.inject.Singleton;
import com.mongodb.MongoClient;
import org.eclipse.jetty.util.StringUtil;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.util.List;

@Singleton
public class VideoFileServiceImpl implements VideoFileService {

    private Datastore datastore;

    public VideoFileServiceImpl() {
        final Morphia morphia = new Morphia();
        morphia.mapPackage("org.mongodb.morphia.example");
        final Datastore datastore = morphia.createDatastore(new MongoClient("192.168.64.2", 27017), "test");
        datastore.ensureIndexes();
    }


    @Override
    public void save(VideoFile videoFile) {
        datastore.save(videoFile);
    }

    @Override
    public void delete(String id) {
        datastore.delete(VideoFile.class, id);
    }

    @Override
    public void update(VideoFile videoFile) {
        datastore.save(videoFile);
    }

    @Override
    public List<VideoFile> query(String deviceId, String channelId, String startTime, String endTime) {
        Query<VideoFile> query = datastore.createQuery(VideoFile.class);
        if (StringUtil.isNotBlank(deviceId))
            query.field("deviceId").equal(deviceId);
        if (StringUtil.isNotBlank(channelId))
            query.field("channelId").equal(channelId);
        if (StringUtil.isNotBlank(startTime)) {

            query.field("startTime").greaterThanOrEq(startTime);
        }
        if (StringUtil.isNotBlank(endTime)) {
            query.field("endTime").lessThanOrEq(endTime);
        }
        return query.asList();
    }
}
