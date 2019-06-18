package com.wcx.springboot.demo.midware.spring.controller;

import com.wcx.springboot.demo.midware.spring.annotation.TestAnno;
import com.wcx.springboot.demo.midware.spring.annotation.WController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WController
@TestAnno("class")
public class AnnotationController {

    @TestAnno("field")
    private int a;

    /**
     * 注解提取
     *
     * @param args
     */
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        // Class 对象的 isAnnotationPresent() 方法判断它是否应用了某个注解
        boolean hasAnnotation = AnnotationController.class.isAnnotationPresent(TestAnno.class);

        if (hasAnnotation) {
            //提取某一类型注解
            TestAnno testAnnotation = AnnotationController.class.getAnnotation(TestAnno.class);
            System.out.println("class value:" + testAnnotation.value());
        }

        //获取一个成员变量上的注解
        Field a = AnnotationController.class.getDeclaredField("a");
        a.setAccessible(true);
        TestAnno testAnno = a.getAnnotation(TestAnno.class);
        if (testAnno != null) {
            System.out.println("field value:" + testAnno.value());
        }

        Method testMethod = AnnotationController.class.getDeclaredMethod("test");

        if (testMethod != null) {
            // 获取方法中的注解
            Annotation[] ans = testMethod.getAnnotations();
            for (int i = 0; i < ans.length; i++) {
                System.out.println("method testMethod annotation:" + ans[i].annotationType().getSimpleName());
            }
        }

    }

    @TestAnno("method")
    private void test() {
    }
}
