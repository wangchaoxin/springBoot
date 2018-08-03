package com.wcx.springboot.demo.boot.configure;

import com.wcx.springboot.demo.boot.service.UserService;
import com.wcx.springboot.demo.boot.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Configuration 注解相当于在<beans>中配置了Userservice bean
 * 如果配置了@componentScan ，会自动扫描Configuration中配置的bean
 */
@Configuration
@ComponentScan("com.wcx.springboot.demo.boot.service.boot")
@EnableScheduling   /*开启支持定时任务*/
public class CommonBeanFactory {

    /*指定初始化方法和销毁方法*/
    @Bean(initMethod = "init",destroyMethod = "destroy")
    UserService getUserService() {
        return new UserServiceImpl();
    }
}
