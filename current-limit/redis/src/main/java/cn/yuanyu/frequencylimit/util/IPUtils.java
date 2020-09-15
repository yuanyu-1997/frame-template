package cn.yuanyu.frequencylimit.util;


import javax.servlet.http.HttpServletRequest;

public class IPUtils {
    /**
     * 获取客户端请求终端地址
     */
    public static String getClientIP(HttpServletRequest request) {
        // 这里获取IP地址还需要根据各种情况走不同的逻辑
        return request.getRemoteAddr();
    }
}
