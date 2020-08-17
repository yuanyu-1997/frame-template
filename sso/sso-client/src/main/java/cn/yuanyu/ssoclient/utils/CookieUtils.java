package cn.yuanyu.ssoclient.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie基本操作
 */
public class CookieUtils {
    // cookie的有效期默认为30天
    public final static int COOKIE_MAX_AGE = 60 * 60 * 24 * 30;

    /**
     * 添加一个新Cookie
     *
     * @param response HttpServletResponse
     * @param cookie   新cookie
     */
    public static void addCookie(HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
            response.addCookie(cookie);
        }
    }

    /**
     * 添加一个新Cookie
     *
     * @param response    HttpServletResponse
     * @param cookieName  cookie名称
     * @param cookieValue cookie值
     * @param domain      cookie所属的子域
     * @param httpOnly    是否将cookie设置成HttpOnly
     * @param maxAge      设置cookie的最大生存期
     * @param path        设置cookie路径
     * @param secure      是否只允许HTTPS访问
     */
    public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue, String domain,
                                 boolean httpOnly, int maxAge, String path, boolean secure) {
        if (StringUtils.isNoneBlank(cookieName)) {
            if (cookieValue == null) {
                cookieValue = "";
            }

            Cookie newCookie = new Cookie(cookieName, cookieValue);
            if (domain != null) {
                newCookie.setDomain(domain);
            }

            newCookie.setHttpOnly(httpOnly);

            if (maxAge > 0) {
                newCookie.setMaxAge(maxAge);
            }

            if (path == null) {
                newCookie.setPath("/");
            } else {
                newCookie.setPath(path);
            }
            newCookie.setSecure(secure);

            addCookie(response, newCookie);
        }
    }

    /**
     * 添加一个新Cookie
     *
     * @param response    HttpServletResponse
     * @param cookieName  cookie名称
     * @param cookieValue cookie值
     * @param domain      cookie所属的子域
     */
    public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue, String domain) {
        addCookie(response, cookieName, cookieValue, domain, true, COOKIE_MAX_AGE, "/", false);
    }

}
