package com.wcx.springboot.demo.xiangxue.jvm.class_init;

public class SubClazz extends SuperClazz {
	
	static {
		System.out.println("Subclass init!");
	}

}
