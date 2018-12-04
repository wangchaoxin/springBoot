package com.wcx.springboot.demo.jvm.memory;

import java.nio.ByteBuffer;

public class DirectMemory {
    public static void main(String[] args) {
        //-XX:MaxDirectMemorySize=1M分配直接内存大小
        //Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
        //分配10m直接内存
        try {
            ByteBuffer.allocateDirect(1024 * 1024 * 100);
        } catch (Throwable e) {
            //out of memory exception可以被throwable catch到
            System.out.println("catch out of memory exception:");
        }
    }
}
