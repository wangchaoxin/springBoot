package com.wcx.springboot.demo.jvm;

public class PrintMemoryUtil {
    public static void printMemory() {
        //maxMemory为  -Xmx指定值
        System.out.println(String.format("max memory [%s] bytes",Runtime.getRuntime().maxMemory()));

        //剩余内存
        System.out.println(String.format("free memory [%s] bytes",Runtime.getRuntime().freeMemory()));

        //total为当前内存 -Xms指定的值
        System.out.println(String.format("total memory [%s] bytes",Runtime.getRuntime().totalMemory()));
    }
}
