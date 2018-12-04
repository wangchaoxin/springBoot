package com.wcx.springboot.demo.jvm.memory;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

public class SoftRefrence {
    public void testSoftReference() {
        User user = new User(1,"wangchaoxin");
        SoftReference<User> softReference = new SoftReference<>(user);
        user = null;
        System.out.println("before gc softrefrence:"+softReference.get());
        System.gc();  //gc之后不会被回收
        System.out.println("after gc softrefrence:"+softReference.get());
        //申请大内存
        try {
            ByteBuffer.allocate(1024 * 1024 * 10);
        } catch (Throwable e) {
            //内存溢出之后，软引用被回收
            System.out.println("after out of memory:"+softReference.get());
        }
    }

    /**
     * weakreference 垃圾回收之后被回收，对象为null
     */
    public void testWeakRefrence() {
        User user = new User(1,"wangchaoxin");
        WeakReference<User> softReference = new WeakReference<>(user);
        user = null;
        System.out.println("before gc softrefrence:"+softReference.get());
        System.gc();
        System.out.println("after gc softrefrence:"+softReference.get());
    }
}
