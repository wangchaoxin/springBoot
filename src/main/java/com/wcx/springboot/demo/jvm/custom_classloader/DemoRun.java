package com.wcx.springboot.demo.jvm.custom_classloader;

public class DemoRun {

    public static void main(String[] args) throws Exception {
        CustomClassLoader demoCustomClassLoader 
        	= new CustomClassLoader("My ClassLoader");
        demoCustomClassLoader.setBasePath("D:\\ncs\\projects\\springboot\\build\\classes\\java\\main\\");
        Class<?> clazz = 
        		demoCustomClassLoader.findClass("com.wcx.springboot.demo.jvm.custom_classloader.DemoUser");
        System.out.println(clazz.getClassLoader());
        Object o = clazz.newInstance();
        System.out.println(o);
        //new User(xxx,yyyy,ddd);//
    }
}
