package com.wcx.springboot.demo.java.http;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static String get(String url) throws IOException {
        if (StringUtils.isBlank(url)) {
            return StringUtils.EMPTY;
        }
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity1 = response.getEntity();
            String result = EntityUtils.toString(entity1);
            EntityUtils.consume(entity1);
            return result;
        } finally {
            response.close();
        }
    }

    public static String post(String url, Map<String, String> parameters) throws IOException {
        if (StringUtils.isBlank(url)) {
            return StringUtils.EMPTY;
        }
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
//        httpPost.setEntity(new UrlEncodedFormEntity(params));
        httpPost.setEntity(new StringEntity(JSON.toJSONString(parameters)));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity());
        return result;
    }
    public static String delete(String url) throws IOException {
        if (StringUtils.isBlank(url)) {
            return StringUtils.EMPTY;
        }
        HttpDelete httpDelete = new HttpDelete(url);
        CloseableHttpResponse response = httpclient.execute(httpDelete);
        try {
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            return result;
        } finally {
            response.close();
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(System.currentTimeMillis());
            String get = get("http://localhost:9000/devices?pindex=1&psize=10");
            System.out.println(get);

            Map<String, String> map = new HashMap<>();
            map.put("name", "device-1");
            map.put("host", "10.20.0.219");
            map.put("port", "8000");
            map.put("username", "");
            map.put("password", "");
            map.put("deviceKey", "stream1");
            map.put("modelId", "general-rtsp");
            String post = post("http://localhost:9000/devices", map);
            System.out.println(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
