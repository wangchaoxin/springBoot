package com.wcx.springboot.demo.source;

import java.io.FileInputStream;
import java.io.IOException;

public class EnvironmentEx {


    public static void main(String[] args) {
        String line = System.getProperty("Arguments");
        if(line != null) {
            String str[] = line.split(",");
            for(int i=1;i<str.length;i++){
                String arr[] = str[i].split("=");
                System.out.println("Key = " + arr[0]);
                System.out.println("Value = " +  arr[1]);
            }
        }
    }

    /**
     * -D 设置多个环境变量,通过System.getProperty获取
     * -DArguments=a=1,b=2,c=3,d=4,e=cow
     */
    public void test() throws IOException {
        //将自定义的properties文件加载到系统变量中
        System.getProperties().load(new FileInputStream("my.properties"));
    }


}
