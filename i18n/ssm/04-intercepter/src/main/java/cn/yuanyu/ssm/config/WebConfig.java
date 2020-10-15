package cn.yuanyu.ssm.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
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

    // SessionLocaleResolver + LocaleChangeInterceptor
    // 区域信息从Session中拿
    @Bean("localeResolver") // ID固定
    public SessionLocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }
    //org.springframework.web.servlet.i18n.LocaleChangeInterceptor#preHandle
    //@Override
    //public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws ServletException {
    //	String newLocale = request.getParameter(getParamName());
    //	if (newLocale != null) {
    //		if (checkHttpMethod(request.getMethod())) {
    //			// 获取
    //			LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
    //			if (localeResolver == null) {
    //				throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
    //			}
    //			try {
    //				// 设置
    //				localeResolver.setLocale(request, response, parseLocaleValue(newLocale));
    //			}catch (IllegalArgumentException ex) {
    //				if (isIgnoreInvalidLocale()) {
    //					if (logger.isDebugEnabled()) {
    //						logger.debug("Ignoring invalid locale value [" + newLocale + "]: " + ex.getMessage());
    //					}
    //				}else {
    //					throw ex;
    //				}
    //			}
    //		}
    //	}
    //	// Proceed in any case.
    //	return true;
    //}
    // 配置拦截器 LocaleChangeInterceptor
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor()).addPathPatterns("/**");;
    }
}