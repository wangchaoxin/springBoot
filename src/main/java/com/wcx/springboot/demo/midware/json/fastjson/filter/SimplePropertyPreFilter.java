package com.wcx.springboot.demo.midware.json.fastjson.filter;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;

/**
 * 过滤属性，可以设置需要和不需要序列化的属性
 */
public class SimplePropertyPreFilter implements PropertyPreFilter  {

    public SimplePropertyPreFilter(String... properties){
        this(null, properties);
    }

    public SimplePropertyPreFilter(Class<?> clazz, String... properties){
        // ... ...
    }

    @Override
    public boolean apply(JSONSerializer serializer, Object object, String name) {
        return false;
    }
}
