package cn.yuanyu.uaa.config;

import cn.yuanyu.uaa.interceptor.LoginInterceptor;
import cn.yuanyu.uaa.interceptor.SsoAccessDomainInterceptor;
import cn.yuanyu.uaa.interceptor.SsoAccessTokenInterceptor;
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
        registry.addInterceptor(loginInterceptor).addPathPatterns("/user/**", "/sso/token");
        // 校验请求获取Token的回调URL是否在白名单中
        registry.addInterceptor(ssoAccessDomainInterceptor).addPathPatterns("/sso/token");
        // 校验 Access Token 是否为空以及 Access Token 是否已经失效
        registry.addInterceptor(ssoAccessTokenInterceptor).addPathPatterns("/sso/verify");

    }
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private SsoAccessDomainInterceptor ssoAccessDomainInterceptor;

    @Autowired
    private SsoAccessTokenInterceptor ssoAccessTokenInterceptor;


}
