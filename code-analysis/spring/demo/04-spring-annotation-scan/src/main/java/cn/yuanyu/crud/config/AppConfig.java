package cn.yuanyu.crud.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @author yuanyu
 */
// @ComponentScan
// value            : 指定要扫描的包
// excludeFilters   : 指定扫描的时候按照什么规则排除那些组件
// includeFilters   : 指定扫描的时候只需要包含那些组件（需要禁用掉默认的规则）


//FilterType.ANNOTATION             : 按照注解
//FilterType.ASSIGNABLE_TYPE        : 按照给定的类型
//FilterType.ASPECTJ                : 使用ASPECTJ表达式
//FilterType.REGEX                  : 使用正则指定
//FilterType.CUSTOM                 : 使用自定义规则

//1
//@Configuration
//@ComponentScan(basePackages = "cn.yuanyu.crud",
//        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = {Controller.class}))

@Configuration
@ComponentScan(basePackages = "cn.yuanyu.crud"
        , includeFilters = @Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
        , useDefaultFilters = false)
public class AppConfig {

}
