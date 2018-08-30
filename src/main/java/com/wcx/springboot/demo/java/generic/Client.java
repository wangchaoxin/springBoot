package com.wcx.springboot.demo.java.generic;

public class Client {
    public static void main(String[] args) {
        Box<String> box = new Box<>("test");
        Box<Number> box1 = new Box<>(2);
//        System.out.println(box.getData());

        Object data = getData(box);
        System.out.println(data);
        getData(box1);

    }


    /**
     * 在逻辑上Box<Number>不能视为Box<Integer>的父类,所以需要通配符
     *
     * @param box
     */
    public static Object getData(Box<?> box) {
        System.out.println(box.getData());
        return box.getData();
    }
}
