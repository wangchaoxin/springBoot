package com.wcx.springboot.demo.xiangxue.jvm.class_init;

public class Main {
	
	public static void main(String[] args) {
		//System.out.println(SubClazz.value);
		//SuperClazz[] sca = new SuperClazz[10];

		//调用常量，常量直接编译在Main class的常量池中，不调用SubClass类,
//		System.out.println(SubClazz.HELLOWORLD);
		System.out.println(SubClazz.WHAT);
		
	}

}
