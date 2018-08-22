package com.wcx.springboot.demo.spring.properties;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class FileLoader {
    public static void main(String[] args) throws IOException {
        FileLoader fileLoader = new FileLoader();
        File file = fileLoader.loadFile("properties/a.properties");

        Properties properties = fileLoader.loadProperties("properties/a.properties");
        String name = properties.getProperty("name");
        System.out.println("end");

        //转义符测试:“.”和“|”都是转义字符必须得加"\\";
        String a="a.b.c";
        String[] split = a.split("\\.");
        System.out.println("");
    }

    public File loadFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        /**
         getResource()方法会去classpath下找这个文件，获取到url resource, 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
         */
        URL url = classLoader.getResource(fileName);
        /**
         * url.getFile() 得到这个文件的绝对路径
         */
        if (url != null) {
            return new File(url.getFile());
        }
        return null;
    }

    public Properties loadProperties(String fileName) throws IOException {
        Properties properties = new Properties();
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("properties/a.properties");
        properties.load(input);
        return properties;
    }
}
