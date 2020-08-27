package cn.yuanyu.qrcode.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	// 保存路径,根路径
	private static final String COOKIE_PATH = "/";

	/**
	 * 保存
	 *
	 * @param response
	 * @param key
	 * @param value
	 * @param maxAge
	 */
	private static void set(HttpServletRequest request, HttpServletResponse response, String key, String value, String domain, String path, int maxAge, boolean isHttpOnly) {
		Cookie cookie = new Cookie(key, value);
		if (domain != null) {
			cookie.setDomain(domain);
		}
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		cookie.setHttpOnly(isHttpOnly);
		response.addCookie(cookie);
	}


	/**
	 * 查询Cookie
	 *
	 * @param request
	 * @param key
	 */
	private static Cookie get(HttpServletRequest request, String key) {
		Cookie[] arr_cookie = request.getCookies();
		if (arr_cookie != null && arr_cookie.length > 0) {
			for (Cookie cookie : arr_cookie) {
				if (cookie.getName().equals(key)) {
					return cookie;
				}
			}
		}
		return null;
	}

	/**
	 * 删除Cookie
	 *
	 * @param request
	 * @param response
	 * @param key
	 */
	public static void remove(HttpServletRequest request, HttpServletResponse response, String key) {
		Cookie cookie = get(request, key);
		if (cookie != null) {
			set(request, response, key, "", null, COOKIE_PATH, 0, true);
		}
	}

}