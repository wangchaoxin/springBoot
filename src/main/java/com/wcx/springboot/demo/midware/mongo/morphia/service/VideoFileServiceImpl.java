package com.wcx.springboot.demo.midware.mongo.morphia.service;

import com.google.inject.Singleton;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
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

    /**
     * 1 and 2 and 3 and (4 or 5)这类查询
     * @param deviceId
     * @param channelId
     * @param streamId
     * @param startTime
     * @param endTime
     * @return
     */
    public List<VideoFile> query(int deviceId, int channelId, int streamId, long startTime, long endTime) {
        Query<VideoFile> query = datastore.createQuery(VideoFile.class);
        if (deviceId >= 0)
            query.field("deviceId").equal(deviceId);
        if (channelId >= 0)
            query.field("channelId").equal(channelId);
        if (streamId >= 0)
            query.field("streamId").equal(streamId);
        query.and(
                query.or(
                        query.criteria("startTime").lessThanOrEq(startTime)
                                .criteria("endTime").greaterThanOrEq(startTime),
                        query.criteria("startTime").lessThanOrEq(endTime)
                                .criteria("endTime").greaterThanOrEq(endTime),
                        query.criteria("startTime").greaterThanOrEq(startTime)
                                .criteria("endTime").lessThanOrEq(endTime)
                )
        );
        return query.asList();
    }

    /**
     * 如果
     * @param id
     * @return
     */
    @Override
    public VideoFile getById(String id) {
        return datastore.get(VideoFile.class,new ObjectId(id));
    }
}
