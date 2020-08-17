package cn.yuanyu.oauthclient.interceptor;

import cn.yuanyu.oauthclient.common.Constants;
import cn.yuanyu.oauthclient.common.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;

/**
 * 定义一些页面需要做登录检查
 */
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * 检查是否已经登录
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //获取session中存储的token
        String accessToken = (String) session.getAttribute(Constants.SESSION_ACCESS_TOKEN);
        if (StringUtils.isNoneBlank(accessToken)) {
            return true;
        } else {
            //如果token不存在，则跳转等登录页面
            String redirectUrl = request.getContextPath() + "/login?redirect_uri=" + SpringContextUtils.getRequestUrl(request);
            log.info("重定向地址 => {}", URLDecoder.decode(redirectUrl, "UTF-8"));
            response.sendRedirect(redirectUrl);
            return false;
        }
    }
}
