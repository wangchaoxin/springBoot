package com.wcx.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 14.2 Locating the Main Application Class
 * We generally recommend that you locate your main application class in a root package above other classes.
 * The @SpringBootApplication annotation is often placed on your main
 * class, and it implicitly defines a base “search package” for certain
 * items. For example, if you are writing a JPA application,
 * the package of the @SpringBootApplication annotated class is used
 * to search for @Entity items. Using a root package also allows
 * component scan to apply only on your project.
 */
@SpringBootApplication  //开启组件扫描和自动配置
public class DemoApplication {
	//负责引导应用程序
	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}
}
