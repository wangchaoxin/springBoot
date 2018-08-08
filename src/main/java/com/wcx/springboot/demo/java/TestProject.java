package com.wcx.springboot.demo.java;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class TestProject {
    public static void main(String[] args) {
        DeviceReport deviceReport=new DeviceReport("result","reason",1,"name","10.10.10.1",38,"user","pass");
        String s = new Gson().toJson(deviceReport);
        System.out.println(s);
    }


    static class DeviceReport
    {
        public String result;
        public String reason;
        public int id;
        public String name;
        public String host;
        public int port;
        public String user;
        public String pass;
        public String key;
        @SerializedName("login-time")
        public String loginTime;
        @SerializedName("login-count")
        public String loginCount;
        @SerializedName("login-result")
        public String loginResult;
        @SerializedName("video-recording")
        public String videoRecording;
        @SerializedName("snapshot-recording")
        public String snapshotRecording;
        @SerializedName("snapshot-interval")
        public String snapshotInterval;
        public double fps;
        @SerializedName("last-frame")
        public String lastFrame;

        public DeviceReport(String result, String reason, int id, String name, String host, int port, String user, String pass) {
            this.result = result;
            this.reason = reason;
            this.id = id;
            this.name = name;
            this.host = host;
            this.port = port;
            this.user = user;
            this.pass = pass;
        }
    }
}
