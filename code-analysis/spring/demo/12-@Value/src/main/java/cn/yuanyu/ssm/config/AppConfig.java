package cn.yuanyu.ssm.config;

import cn.yuanyu.ssm.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// 使用@PropertySource读取外部配置文件中的 k/v 保存到运行的环境变量中，加载完外部的配置文件以后使用 ${} 取出配置文件的值
@PropertySource("classpath:person.properties") // <context:property-placeholder location="classpath:person.properties"/>
@Configuration
public class AppConfig {
    @Bean
    public Person cxk() {
        return new Person();
    }
}
