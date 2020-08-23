package cn.yuanyu.websocket.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 加载配置文件
 *
 * @author yuanyu
 */
public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    // spring
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfig.class};
    }

    // spring mvc
    // websocket
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class, WebSocketConfig.class};
    }

    //
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}