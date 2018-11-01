package com.wcx.springboot.demo.midware.json.fastjson.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import org.junit.Test;

public class ClassNameFilterTest {
    @Test
    /**
     * 过滤序列化属性,可以改属性名
     */
    public void test_filter() throws Exception {
        NameFilter upcaseNameFilter = new NameFilter() {
            public String process(Object object, String name, Object value) {
                if (name.equals("password")) {
                    value = "223232";
                    return "encrypt";
                }
                return name.toUpperCase();
            }
        };
        SerializeConfig.getGlobalInstance() //
                .addFilter(A.class, upcaseNameFilter);

        System.out.println(JSON.toJSONString(new A()));

    }

    public static class A {
        public int id;
        public String password;
    }

    public static class B {
        public int id;
    }

}
