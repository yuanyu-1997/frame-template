package cn.yuanyu.ssm.config;

import cn.yuanyu.ssm.aop.LogAspects;
import cn.yuanyu.ssm.bean.Red;
import cn.yuanyu.ssm.service.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
//<!-- 开启基于注解版的切面功能 -->
//<aop:aspectj-autoproxy></aop:aspectj-autoproxy>

@Configuration
@ComponentScan("cn.yuanyu.ssm")
@EnableAspectJAutoProxy
public class AppConfig{

    // 业务逻辑类
    @Bean
    public MathCalculator mathCalculator(){
        return new MathCalculator();
    }

    // 切面类
    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }

    @Bean
    public Red red(){
        return new Red();
    }


}