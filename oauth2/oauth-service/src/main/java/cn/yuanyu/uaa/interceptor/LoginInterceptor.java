package cn.yuanyu.uaa.interceptor;

import cn.yuanyu.uaa.common.Constants;
import cn.yuanyu.uaa.common.SpringContextUtils;
import cn.yuanyu.uaa.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;

/**
 * 定义一些页面需要做登录检查
 */
@Slf4j
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    /**
     * 检查是否已经登录
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        // 获取session中存储的token
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        if (user != null) {
            return true;
        } else {
            // 如果token不存在，则跳转到登录页面
            String redirectUrl = request.getContextPath() + "/login?redirect=" + SpringContextUtils.getRequestUrl(request);
            log.info("token不存在，重定向页面 => {}", URLDecoder.decode(redirectUrl,"UTF-8"));
            response.sendRedirect(redirectUrl);
            return false;
        }
    }
}
