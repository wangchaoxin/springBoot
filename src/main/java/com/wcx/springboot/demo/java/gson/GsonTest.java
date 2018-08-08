package com.wcx.springboot.demo.java.gson;

import com.google.gson.Gson;

public class GsonTest {

    public static void main(String[] args) {
        testGson();
    }

    public static void testGson(){
        Gson gson=new Gson();
        String json="{\"code\": 200, \"serviceId\": \"device.ndjbjhg\", \"requestId\": \"test-abcd\", \"type\": \"device-service\", \"action\": \"add-device\", \"body\": \"\" }";
        MqResponse mqResponse = gson.fromJson(json, MqResponse.class);
        System.out.println("success");
    }
}
