package cn.yuanyu.userapi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author yuanyu
 */
@Configuration
@ComponentScan(basePackages = "cn.yuanyu.userapi",
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = {Controller.class}))
public class AppConfig {

}
