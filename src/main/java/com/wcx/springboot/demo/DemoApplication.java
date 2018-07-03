package com.wcx.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //开启组件扫描和自动配置
public class DemoApplication {
	//负责引导应用程序
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
