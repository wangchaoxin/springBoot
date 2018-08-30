package com.wcx.springboot.demo.java.reflector;

import com.wcx.springboot.demo.boot.model.User;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;

public class ClassUtil {


    public static void main(String[] args) throws ClassNotFoundException {

        try {
            ClassUtil cl = new ClassUtil();
            ArrayList classes = PackageUtil.getClasses("com.wcx.springboot.demo.java.generic");

            /*反射实例化类*/
            Class<?> userClass = Class.forName("com.wcx.springboot.demo.boot.model.User");
            Object userObj = userClass.newInstance();
            User user = null;
            /*将超类转换成子类之前，用instanceof 进行检查*/
            if (userObj instanceof User) {
                user = (User) userClass.newInstance();
                user.setId(1);
                user.setName("wang");
            }

            /*调用反射类方法*/
            Method m = userClass.getDeclaredMethod("setName", String.class);
            Method m1 = userClass.getMethod("setName", String.class);
            m.invoke(user, "chao");
            System.out.println(user.getName());

            /*访问私有成员变量*/
            Field field = userClass.getDeclaredField("id");
            field.setAccessible(true);//设置允许访问，默认不允许访问私有域
            field.set(user, 10);
            String name = field.get(user).toString();

            /*测试assignableFrom*/
            boolean assignableFrom = Object.class.isAssignableFrom(String.class);


            Class<?> ia = Class.forName("com.wcx.springboot.demo.java.reflector.inter.IA");
            Class<?> a = Class.forName("com.wcx.springboot.demo.java.reflector.A");

            System.out.println(ia.isAssignableFrom(a));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ClassUtil() throws IOException {
        /*file:/D:/ncs/projects/springboot/out/production/classes/*/
        /*在该路径下寻找类*/
        URL resource = getClass().getResource("/");
        System.out.println(resource);

    }




}
