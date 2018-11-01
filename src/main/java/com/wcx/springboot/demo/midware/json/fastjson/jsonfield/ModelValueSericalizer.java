package com.wcx.springboot.demo.midware.json.fastjson.jsonfield;


import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * SONField支持新的定制化配置serializeUsing，可以单独对某一个类的某个属性定制序列化
 */
public class ModelValueSericalizer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        Integer value = (Integer) object;
        String text = value + "元";
        serializer.write(text);
    }
}
