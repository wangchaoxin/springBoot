package com.wcx.springboot.demo.midware.json.gson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

import java.util.TreeMap;

public class GsonTest {

    public static void main(String[] args) {
        testGson();
    }

    public static void testGson() {
        Gson gson = new Gson();
        String json = "{\"code\": 200, \"serviceId\": \"device.ndjbjhg\", \"requestId\": \"testSoftReference-abcd\", \"type\": \"device-service\", \"action\": \"add-device\", \"body\": \"\" }";

        /*转换到实体*/
        MqResponse mqResponse = gson.fromJson(json, MqResponse.class);

        /*获取jsonObject*/
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        System.out.println("success");

        /*转换到json string*/
        String s = gson.toJson(mqResponse);

        /*将enum转换成json **/
        String enumJson = gson.toJson(ApiResponse.FAIL);

    }

    //测试是否有重复的key
    @Test
    public void testDuplicateKey() {
        try {
            MyMap map;
            //this does not detect duplicate 'key'
            String json = "{'a':'x','aa':{'key1':'x','key':'y'}}";
//            JSONObject  ob = new JSONObject(json);
            map = new Gson().fromJson(json, MyMap.class);

            //As expected , this call fails with  Exception in thread "main" com.google.gson.JsonSyntaxException: duplicate key: a
            map = new Gson().fromJson("{'a':'x','a':'y'}", MyMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class MyMap<A, B> extends TreeMap<A, B> {
        @Override
        public B put(A key, B value) {
            if (containsKey(key)) {
                //TODO : Replace, discard...do whatever you want
                throw new IllegalArgumentException("duplicate key");
            }
            return super.put(key, value);
        }
    }
}
