package cn.yuanyu.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author yuanyu
 */
@Configuration
@ComponentScan(basePackages = "cn.yuanyu.aop")
@EnableAspectJAutoProxy // 启用AspectJ注解处理器，等同于xml中的<aop:aspectj-autoproxy/>
public class AppConfig {

}
