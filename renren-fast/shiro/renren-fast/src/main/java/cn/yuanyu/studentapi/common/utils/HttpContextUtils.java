

package cn.yuanyu.studentapi.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuanyu
 */
public class HttpContextUtils {
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    /**
     * 获取请求参数中的Origin属性
     */
    public static String getOrigin() {
        HttpServletRequest request = getHttpServletRequest();
        return request.getHeader("Origin");
    }
}
