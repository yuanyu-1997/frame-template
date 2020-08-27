package cn.yuanyu.websocket.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * spring mvc 配置文件
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cn.yuanyu.websocket.controller"
        , includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)}
        , useDefaultFilters = false)
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver resourceVisitor(){
        InternalResourceViewResolver res = new InternalResourceViewResolver();
        res.setPrefix("/WEB-INF/views/");
        res.setSuffix(".jsp");
        return res;
    }

}