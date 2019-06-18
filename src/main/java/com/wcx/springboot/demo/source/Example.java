package com.wcx.springboot.demo.source;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class Example {
    public static final String NAME;

    //thread local用法： ThreadLocal 适用于变量在线程间隔离且在方法间共享的场景，类中的多个方法都可操作thread local变量
    private static final ThreadLocal<StringBuilder> threadLocalStringBuilder =
            ThreadLocal.withInitial(StringBuilder::new);


    //static中初始化静态变量
    static {
        NAME = "wang";
    }

    /**
     * 1 检查参数，抛出异常
     *
     * @param a
     */
    public void test(String a) {
        if (a == null)
            throw new NullPointerException();
        //检查null
        Objects.requireNonNull(a);
        if (a.equals("1"))
            //参数不正确异常
            throw new IllegalArgumentException();
    }

    /**
     * class,array,basic
     */
    @Test
    public Object test1() {
        Integer a = 1;
        //判断是否是基类，或者继承了接口
        boolean b = a.getClass().isAssignableFrom(Object.class);
        boolean b1 = Object.class.isAssignableFrom(a.getClass());
        System.out.println(b);
        System.out.println(b1);
        //判断实例是否属于某个类
        Object.class.isInstance(b);
        if (a instanceof Integer) {

        }
        //数组toString()，直接toString不可用,得用Arrays.toString()
        byte[] arr = {1, 2, 3};
        Person[] people = new Person[1];
        System.out.println(arr);
        System.out.println(Arrays.toString(arr));
        //数组转list
        List<Person> people1 = Arrays.asList(people);
        //获取环境变量
        String property = System.getProperty("ConnectionStateManagerSize", null);

        //switch case
        int qty = 0;
        switch (qty) {
            case 0: {
                return null;
            }
            case 1: {
                return null;
            }
            default: {
                throw new IllegalStateException("More than 1 auth has been added");
            }
        }

    }

    //等待某段时间后结束
    public void waitSomeTime(long millis) {
        long base = System.currentTimeMillis();
        long now = 0;
        while (true) {
            long delay = millis - now;
            if (delay <= 0) {
                break;
            }
            now = System.currentTimeMillis() - base;
        }
    }

    private enum State {
        LATENT,
        STARTED,
        CLOSED
    }

    /**
     * concurrent,atomic
     */
    @Test
    public void test2() {
        //atomic boolean
        AtomicBoolean initialConnectMessageSent = new AtomicBoolean(false);
        initialConnectMessageSent.compareAndSet(false, true);
        //atomica refrence
        AtomicReference<State> state = new AtomicReference<State>(State.LATENT);
        state.compareAndSet(State.LATENT, State.CLOSED);
        Map<String, Object> map = new HashMap<>();
        //get and set时锁住map
        synchronized (map) {
            if (map.containsKey("key")) {
                map.put("key", new Object());
            }
        }
    }

    /**
     * ThreadFactory用法
     */
    @Test
    public ThreadFactory test3() {
        final AtomicLong count = new AtomicLong(0);
        return new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread("test-" + count.getAndIncrement());
            }
        };
    }

    public class Person {


        private String name;

        public String getName() {
            return name;
        }

        //equal 写法
        @Override
        public boolean equals(Object obj) {
            if ((null == obj) || (obj.getClass() != Person.class))
                return false;
            Person person = (Person) obj;
            return this.getName().equals(person.getName());
        }
    }
}
