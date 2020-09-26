package cn.yuanyu.config;

import cn.yuanyu.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 使用配置类代替配置文件
@Configuration  // 告诉Spring这是一个配置类
public class AppConfig {
    // 给容器中注册一个Bean，类型为返回值的类型，id默认是用方法名作为id
    @Bean //@Bean("kun")
    public Person cxk() {
        return new Person("蔡徐坤", 58);
    }
}
