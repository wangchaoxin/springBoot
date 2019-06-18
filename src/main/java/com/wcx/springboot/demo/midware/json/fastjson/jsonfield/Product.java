package com.wcx.springboot.demo.midware.json.fastjson.jsonfield;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 作用在getter/setter上
 * 当作用在setter方法上时，就相当于根据 name 到 json中寻找对应的值，并调用该setter对象赋值。
 * 当作用在getter上时，在bean转换为json时，其key值为name定义的值
 */
public class Product {
    private String productName;

    @JSONField(name = "PRODUCTNAME")
    public String getProductName() {
        return productName;
    }
    @JSONField(name = "PRODUCTNAME")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Product(String productName) {
        this.productName = productName;
    }

    public static void main(String[] args) {
        Product product = new Product("bread");
        String json = JSON.toJSONString(product);
        System.out.println(json);

        String s = "{\"PRODUCTNAME\":\"bread\"}";
        Product product1 = JSON.parseObject(s, Product.class);
        Product product2 = JSONObject.toJavaObject(JSONObject.parseObject(s), Product.class);
        System.out.println(product1);
    }
}
