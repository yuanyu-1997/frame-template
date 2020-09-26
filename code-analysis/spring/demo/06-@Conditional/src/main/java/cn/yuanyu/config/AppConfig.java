package cn.yuanyu.config;

import cn.yuanyu.bean.Person;
import cn.yuanyu.config.condition.LinuxCondition;
import cn.yuanyu.config.condition.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 根据系统环境加载不同的bean
 */
// @Conditional放在整类个上，用于类中组件统一设置，满足当前条件这个类中配置的所有bean才会注册生效
@Configuration
public class AppConfig {
    // 系统是windows才注册
    @Bean("bill")
    @Conditional(value = WindowsCondition.class)
    public Person bill() {
        return new Person("Bill Gates", 62);
    }

    // 系统是linux才注册
    @Conditional(value = LinuxCondition.class)
    @Bean("linus")
    public Person linus() {
        return new Person("linus", 48);
    }
}
