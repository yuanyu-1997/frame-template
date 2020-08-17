package cn.yuanyu.ssoclient.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Spring工具类
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

	private static ConfigurableApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = (ConfigurableApplicationContext) context;
	}

	/**
	 * 获取HttpServletRequest
	 */
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attributes.getRequest();
	}

    /**
     * 获取完整的请求URL
     */
	public static String getRequestUrl(HttpServletRequest request){
        //当前请求路径
        String currentUrl = request.getRequestURL().toString();
        //请求参数
        String queryString = request.getQueryString();
        if(!StringUtils.isEmpty(queryString)){
            currentUrl = currentUrl + "?" + queryString;
        }
        String result = "";
        try {
            result = URLEncoder.encode(currentUrl,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            //ignore
        }

        return result;
    }

}
