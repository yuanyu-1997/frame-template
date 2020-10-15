package cn.yuanyu.ssm.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author yuanyu
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cn.yuanyu.ssm.controller"
        , includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)}
        , useDefaultFilters = false)
public class WebConfig implements WebMvcConfigurer {
    /**
     * 视图解析器
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * 国际化
     */
    @Bean("messageSource") // ID固定
    public ResourceBundleMessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
        bundleMessageSource.setBasenames("i18n/login/login");
        bundleMessageSource.setDefaultEncoding("UTF-8");
        return bundleMessageSource;
    }

    // 自定义区域信息解析器
    @Bean("localeResolver") // ID固定
    public MyLocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

}