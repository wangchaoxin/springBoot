package com.wcx.springboot.demo.java.enumer;

/**
 * 枚举类型的每一个值都将映射到 protected Enum(String name, int ordinal)
 * 构造函数中，在这里，每个值的名称都被转换成一个字符串，并且序数设置表示了此设置被创建的顺序
 */
public enum Age {
    MON, TUE, WED, THU, FRI, SAT, SUN;

    public static void main(String[] args) {
        System.out.println(Age.MON.ordinal());
        System.out.println(Age.valueOf("MON").ordinal()) ;
    }

}

