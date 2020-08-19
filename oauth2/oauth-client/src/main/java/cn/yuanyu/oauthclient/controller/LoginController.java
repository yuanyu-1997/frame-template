package cn.yuanyu.oauthclient.controller;

import cn.yuanyu.oauthclient.common.Constants;
import cn.yuanyu.oauthclient.model.AuthorizationResponse;
import cn.yuanyu.oauthclient.model.User;
import cn.yuanyu.oauthclient.utils.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.MessageFormat;

/**
 * 登录
 */
@Slf4j
@Controller
public class LoginController {

    @Value("${own.oauth2.client-id}")
    private String clientId;

    @Value("${own.oauth2.scope}")
    private String scope;

    @Value("${own.oauth2.client-secret}")
    private String clientSecret;

    @Value("${own.oauth2.user-authorization-uri}")
    private String authorizationUri;

    @Value("${own.oauth2.access-token-uri}")
    private String accessTokenUri;

    @Value("${own.oauth2.resource.userInfoUri}")
    private String userInfoUri;

    /**
     * 登录验证（实际登录调用认证服务器）
     *
     * @param redirectUrl 当前系统登录成功之后的回调URL
     * @param code        当前系统请求认证服务器成功之后返回的Authorization Code
     */
    // http://localhost:2500/xxx-client/login
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(value = "redirect", required = false) String redirectUrl,
                              @RequestParam(value = "code", required = false) String code, HttpServletRequest request) {
        // 最后重定向的URL
        String resultUrl = "redirect:";
        HttpSession session = request.getSession();

        // TODO
        String redirectUri = "http://127.0.0.1:2500/xxx-client/login";

        // code为空，则说明当前请求不是认证服务器的回调请求，则重定向URL到认证服务器登录
        if(!StringUtils.isBlank(redirectUrl)){
            // 如果存在回调URL，则将这个URL添加到session
            if (StringUtils.isNoneBlank(redirectUrl)) {
                session.setAttribute(Constants.SESSION_LOGIN_REDIRECT_URL, redirectUrl);
            }
            // 生成随机的状态码，用于防止CSRF攻击
            String status = EncryptUtils.getRandomStr1(6);
            session.setAttribute(Constants.SESSION_AUTH_CODE_STATUS, status);
            // 拼装请求 Authorization Code 的地址
            resultUrl += MessageFormat.format(authorizationUri, clientId, status, redirectUri);
        }
        else {
            // 2.通过 Authorization Code 获取 Access Token
            AuthorizationResponse response = new RestTemplate().getForObject(accessTokenUri, AuthorizationResponse.class, clientId, clientSecret, code, redirectUri);
            //如果正常返回
            if (response != null && StringUtils.isNoneBlank(response.getAccess_token())) {
                System.out.println(response);
                // 2.1 将Access Token存到session
                session.setAttribute(Constants.SESSION_ACCESS_TOKEN, response.getAccess_token());
                //2.2 再次查询用户基础信息，并将用户ID存到session
                User user = new RestTemplate().getForObject(userInfoUri, User.class, response.getAccess_token());
                if (user != null && StringUtils.isNoneBlank(user.getUsername())) {
                    session.setAttribute(Constants.SESSION_USER, user);
                }
            }
            // 3.从session中获取回调URL，并返回
            redirectUrl = (String) session.getAttribute(Constants.SESSION_LOGIN_REDIRECT_URL);
            session.removeAttribute("redirectUrl");
            if (StringUtils.isNoneBlank(redirectUrl)) {
                resultUrl += redirectUrl;
            } else {
                resultUrl += "/user/userIndex";
            }
        }
        log.info("重定向地址 => {}", resultUrl);
        return new ModelAndView(resultUrl);
    }

}
