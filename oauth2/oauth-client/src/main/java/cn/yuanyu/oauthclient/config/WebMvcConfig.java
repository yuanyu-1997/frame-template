package cn.yuanyu.oauthclient.config;

import cn.yuanyu.oauthclient.interceptor.AuthInterceptor;
import cn.yuanyu.oauthclient.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web相关配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 视图控制器
     */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
	}

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/**");
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/login");
    }
}
