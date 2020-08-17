package cn.yuanyu.ssoclient.interceptor;

import cn.yuanyu.ssoclient.common.Constants;
import cn.yuanyu.ssoclient.common.SpringContextUtils;
import cn.yuanyu.ssoclient.model.bo.UserBo;
import lombok.extern.slf4j.Slf4j;
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
        //获取session中存储的用户信息
        UserBo user = (UserBo) session.getAttribute(Constants.SESSION_USER);
        if (user != null) {
            return true;
        } else {
            //如果token不存在，则跳转等登录页面
            // redirectUrl 就是当前的url
            String redirectUrl = request.getContextPath() + "/login?redirect=" + SpringContextUtils.getRequestUrl(request);
            log.info("Session中没有用户数据，重定向地址 => {}", URLDecoder.decode(redirectUrl,"UTF-8"));
            response.sendRedirect(redirectUrl);
            return false;
        }
    }
}
