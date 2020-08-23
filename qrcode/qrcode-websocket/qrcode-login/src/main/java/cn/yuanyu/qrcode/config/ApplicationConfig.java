package cn.yuanyu.qrcode.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * spring 配置文件
 *
 * @author yuanyu
 */
@Configuration
@ComponentScan(basePackages = "cn.yuanyu.qrcode"
        , excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
public class ApplicationConfig {
}