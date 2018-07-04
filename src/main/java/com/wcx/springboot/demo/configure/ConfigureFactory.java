package com.wcx.springboot.demo.configure;

import com.wcx.springboot.demo.service.UserService;
import com.wcx.springboot.demo.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Configuration 注解相当于在<beans>中配置了Userservice bean
@Configuration
public class ConfigureFactory {
    @Bean
    UserService getUserService() {
        return new UserServiceImpl();
    }
}
