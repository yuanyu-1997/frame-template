package cn.yuanyu.jsp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// https://segmentfault.com/a/1190000020384247
/**
 * @author yuanyu
 */
public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 加载 spring 容器
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfig.class};
    }

    // 加载 spring mvc
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    // url-mapping
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}