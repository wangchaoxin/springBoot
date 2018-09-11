package com.wcx.springboot.demo.midware.mongo.morphia.service;

import org.mongodb.morphia.annotations.*;

@Entity("videofile")
@Indexes({@Index(value = "deviceId", fields = @Field("deviceId"))})
public class VideoFile {
    @Id
    private String id;
    private String deviceId;
    private String channelId;
    private String startTime;
    private String endTime;
    private String path;
    private boolean isDeleting = false;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public VideoFile(String deviceId, String channelId, String startTime, String endTime, String path) {
        this.deviceId = deviceId;
        this.channelId = channelId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.path = path;
    }

    public boolean isDeleting() {
        return isDeleting;
    }

    public void setDeleting(boolean deleting) {
        isDeleting = deleting;
    }

    public VideoFile() {

    }
}
