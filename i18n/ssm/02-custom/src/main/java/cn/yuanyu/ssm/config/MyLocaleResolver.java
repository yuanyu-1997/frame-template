package cn.yuanyu.ssm.config;


import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * org.springframework.web.servlet.DispatcherServlet#initStrategies(org.springframework.context.ApplicationContext)
 * org.springframework.web.servlet.DispatcherServlet#initLocaleResolver(org.springframework.context.ApplicationContext)
 */
//// org.springframework.web.servlet.DispatcherServlet#initLocaleResolver
//private void initLocaleResolver(ApplicationContext context) {
//	try {
//		// 从容器中找 LOCALE_RESOLVER_BEAN_NAME=localeResolver
//		this.localeResolver = context.getBean(LOCALE_RESOLVER_BEAN_NAME, LocaleResolver.class);
//		if (logger.isTraceEnabled()) {
//			logger.trace("Detected " + this.localeResolver);
//		}
//		else if (logger.isDebugEnabled()) {
//			logger.debug("Detected " + this.localeResolver.getClass().getSimpleName());
//		}
//	}
//	catch (NoSuchBeanDefinitionException ex) {
//		// 使用默认的
//		this.localeResolver = getDefaultStrategy(context, LocaleResolver.class);
//		if (logger.isTraceEnabled()) {
//			logger.trace("No LocaleResolver '" + LOCALE_RESOLVER_BEAN_NAME + "': using default [" + this.localeResolver.getClass().getSimpleName() + "]");
//		}
//	}
//}
public class MyLocaleResolver implements LocaleResolver {

    /**
     * 解析返回Locale
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String localeStr = request.getParameter("locale");
        Locale locale = null;
        // zh_CN
        // 如果带了locale参数就用参数指定的区域信息，如果没带就用请求头的
        if (localeStr != null && !"".equals(localeStr)) {
            locale = new Locale(localeStr.split("_")[0], localeStr.split("_")[1]);
        } else {
            locale = request.getLocale();
        }
        return locale;
    }

    /***
     * 修改Locale（不支持）
     */
    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        throw new UnsupportedOperationException("Cannot change fixed locale - use a different locale resolution strategy");
    }
}
