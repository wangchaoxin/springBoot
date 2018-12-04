package com.wcx.springboot.demo.jvm;

import com.wcx.springboot.demo.jvm.memory.SoftRefrence;

public class Client {

    private static int count = 0;




    //打印最大可用内存  -Xmx32m设置
    private static void maxMemory(String arg) {
        System.out.println(String.format("args [%s],max memory [%sm]", arg,
                Runtime.getRuntime().maxMemory() / 1000 / 1000));
    }

    /**
     * -Xss 指定线程最大栈空间，决定函数调用的最大深度,越大调用的次数越多
     */
    public static void recursion() {
        count++;
        recursion();
    }




    public static void main(String[] args) {
//        maxMemory(args[0]);
        try {
//            recursion();
//            new SoftRefrence().testSoftReference();
            new SoftRefrence().testWeakRefrence();
        } catch (Throwable e) {
            System.gc();
            System.out.println("最大调用次数:"+count);
        }
        System.gc();
    }


}
