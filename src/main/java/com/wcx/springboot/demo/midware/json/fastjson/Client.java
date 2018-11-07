package com.wcx.springboot.demo.midware.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wcx.springboot.demo.midware.json.fastjson.jsonfield.Student;
import com.wcx.springboot.demo.midware.json.fastjson.model.Group;
import com.wcx.springboot.demo.midware.json.fastjson.model.User;

import java.util.Date;

public class Client {
    public static void main(String[] args) {

//        testBasic();
//        testFilter();
        testJsonField();
    }

    /**
     * 测试jsonField
     */
    private static void testJsonField() {
        Student student = new Student();
        System.out.println(JSON.toJSONString(student));
        //parse array
        JSON.parseArray("");
    }

    private static void testFilter() {

    }

    private static void testBasic() {
        Group group = new Group();
        group.setId(0L);
        group.setName("admin");

        User guestUser = new User();
        guestUser.setId(2L);
        guestUser.setName("guest");

        User rootUser = new User();
        rootUser.setId(3L);
        rootUser.setName("root");

        group.addUser(guestUser);
        group.addUser(rootUser);
        //encode
        String jsonString = JSON.toJSONString(group);
        //decode
        Group group1 = JSON.parseObject(jsonString, Group.class);

        //处理时间
        System.out.println("date1:"+JSON.toJSONString(new Date()));
        System.out.println("date2:");
        JSON.toJSONStringWithDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(jsonString);

        //输出空值
        /*空值特别处理
        SerializerFeature	描述
        WriteNullListAsEmpty	将Collection类型字段的字段空值输出为[]
        WriteNullStringAsEmpty	将字符串类型字段的空值输出为空字符串 ""
        WriteNullNumberAsZero	将数值类型字段的空值输出为0
        WriteNullBooleanAsFalse	将Boolean类型字段的空值输出为false*/
        System.out.println("output null value"+JSON.toJSONString(new Group(),SerializerFeature.WriteMapNullValue));
    }



}
