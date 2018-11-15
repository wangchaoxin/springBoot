package com.wcx.springboot.demo.thread;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalClient {
    /**
     * 初始化threadlocal初始值
     */
    private static ThreadLocal<Map> threadLocal = new ThreadLocal<Map>() {
        @Override
        protected Map initialValue() {
            return new HashMap();
        }
    };

    public static void main(String[] args) {
        //get and set
        threadLocal.get();
        threadLocal.set(new HashMap());
    }
}
