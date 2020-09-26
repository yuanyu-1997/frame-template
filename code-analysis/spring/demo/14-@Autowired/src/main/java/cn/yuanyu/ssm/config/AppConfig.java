package cn.yuanyu.ssm.config;

import cn.yuanyu.ssm.bean.Benz;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("cn.yuanyu.ssm")
public class AppConfig {
    @Bean("benz")
    public Benz a() {
        Benz benz = new Benz();
        benz.setTag("a");
        return benz;
    }
    @Bean("benz")
    public Benz b() {
        Benz benz = new Benz();
        benz.setTag("b");
        return benz;
    }
}
