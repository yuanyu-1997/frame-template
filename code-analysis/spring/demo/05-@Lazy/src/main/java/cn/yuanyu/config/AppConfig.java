package cn.yuanyu.config;

import cn.yuanyu.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


@Configuration
public class AppConfig {
    @Lazy
    @Bean
    public Person cxk() {
        System.out.println("cn.yuanyu.config.AppConfig.cxk...");
        return new Person("蔡徐坤", 58);
    }
}
