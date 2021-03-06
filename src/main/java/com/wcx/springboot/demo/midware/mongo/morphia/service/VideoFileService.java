package com.wcx.springboot.demo.midware.mongo.morphia.service;

import java.util.List;

public interface VideoFileService {
    void save(VideoFile videoFile);

    void delete(String id);

    void delete(VideoFile videoFile);

    void update(VideoFile videoFile);

    void updateUseMerge(VideoFile videoFile);

    List<VideoFile> query(String deviceId, String channelId, String startTime, String endTime);

    VideoFile getById(String id);
}
