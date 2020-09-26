package cn.yuanyu.ssm.config;

import cn.yuanyu.ssm.bean.Red;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

// 1）、@Import(要导入到容器中的组件)             : 容器中就会自动注册这个组件，id默认是组件的全类名
// 2）、ImportSelector                        : 返回需要导入的组件的全类名数组
// 3）、ImportBeanDefinitionRegistrar         : 手动注册bean到容器中
@Configuration
@Import({Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class AppConfig {

}
