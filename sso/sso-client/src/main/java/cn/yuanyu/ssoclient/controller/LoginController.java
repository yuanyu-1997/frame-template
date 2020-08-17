package cn.yuanyu.ssoclient.controller;

import cn.yuanyu.ssoclient.common.Constants;
import cn.yuanyu.ssoclient.model.SsoResponse;
import cn.yuanyu.ssoclient.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录
 */
@Slf4j
@Controller
public class LoginController {

    @Value("${own.sso.access-token-uri}")
    private String accessTokenUri;

    @Value("${own.sso.verify-uri}")
    private String verifyUri;


    /**
     * 登录验证（实际登录调用认证服务器）
     *
     * @param redirectUrl 当前系统登录成功之后的回调URL
     * @param code        当前系统请求认证服务器成功之后返回的Token
     */
    // http://127.0.0.1:5000/user-center/login?redirect_uri=http://127.0.0.1:5000/sso-client/user/userIndex
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(value = "redirect", required = false) String redirectUrl,
                              @RequestParam(value = "code", required = false) String code,
                              HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        log.info("登录验证...");
        // 最后重定向的URL
        String resultUrl = "redirect:";
        // 1.code为空，则说明当前请求不是认证服务器的回调请求，则重定向URL到认证服务器登录
        if (StringUtils.isBlank(code)) {
            //如果存在回调URL，则将这个URL添加到session，
            if (StringUtils.isNoneBlank(redirectUrl)) {
                session.setAttribute(Constants.SESSION_LOGIN_REDIRECT_URL, redirectUrl);
            }
            //拼装请求Token的地址
            resultUrl += accessTokenUri;
            log.info("重定向到认证服务器登录 => {}", resultUrl);
        } else {
            //2. 验证Token，并返回用户基本信息、所属角色、访问权限等
            SsoResponse verifyResponse = new RestTemplate().getForObject(verifyUri, SsoResponse.class, code);
            log.info("认证服务器回调请求 => {}", verifyResponse);
            //如果正常返回
            if (verifyResponse != null && StringUtils.isNoneBlank(verifyResponse.getAccess_token())) {
                // 2.1 将用户信息存到session
                session.setAttribute(Constants.SESSION_USER, verifyResponse.getUser_info());
                // 2.2 将Access Token和Refresh Token写到cookie
                CookieUtils.addCookie(response, Constants.COOKIE_ACCESS_TOKEN, verifyResponse.getAccess_token(), request.getServerName());
                CookieUtils.addCookie(response, Constants.COOKIE_REFRESH_TOKEN, verifyResponse.getRefresh_token(), request.getServerName());
            }
            // 3.从session中获取回调URL，并返回
            redirectUrl = (String) session.getAttribute(Constants.SESSION_LOGIN_REDIRECT_URL);
            session.removeAttribute(Constants.SESSION_LOGIN_REDIRECT_URL);
            if (StringUtils.isNoneBlank(redirectUrl)) {
                resultUrl += redirectUrl;
            } else {
                log.error("没有回调地址...");
                resultUrl += "/user/userIndex";
            }
        }
        log.info("重定向地址 => {}", resultUrl);
        return new ModelAndView(resultUrl);
    }
}
