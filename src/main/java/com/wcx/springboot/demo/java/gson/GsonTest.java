package com.wcx.springboot.demo.java.gson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonTest {

    public static void main(String[] args) {
        testGson();
    }

    public static void testGson(){
        Gson gson=new Gson();
        String json="{\"code\": 200, \"serviceId\": \"device.ndjbjhg\", \"requestId\": \"test-abcd\", \"type\": \"device-service\", \"action\": \"add-device\", \"body\": \"\" }";

        /*转换到实体*/
        MqResponse mqResponse = gson.fromJson(json, MqResponse.class);

        /*获取jsonObject*/
        JsonParser jsonParser=new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        System.out.println("success");

        /*转换到json string*/
        String s = gson.toJson(mqResponse);

        /*将enum转换成json **/
        String enumJson = gson.toJson(ApiResponse.FAIL);

    }
}
