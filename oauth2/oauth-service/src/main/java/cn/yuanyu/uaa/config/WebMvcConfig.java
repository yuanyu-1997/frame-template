package cn.yuanyu.uaa.config;

import cn.yuanyu.uaa.interceptor.AuthAccessTokenInterceptor;
import cn.yuanyu.uaa.interceptor.LoginInterceptor;
import cn.yuanyu.uaa.interceptor.OauthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 视图控制器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
    }

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 未登录跳转到登陆页面
        registry.addInterceptor(loginInterceptor).addPathPatterns("/user/**", "/oauth2/authorizePage", "/oauth2/authorize");
        //
        registry.addInterceptor(oauthInterceptor).addPathPatterns("/oauth2/authorize");
        registry.addInterceptor(authAccessTokenInterceptor).addPathPatterns("/api/**");

    }
    @Autowired
    private LoginInterceptor loginInterceptor;


    //
    @Autowired
    private AuthAccessTokenInterceptor authAccessTokenInterceptor;

    @Autowired
    private OauthInterceptor oauthInterceptor;

}
