package cn.yuanyu.oauthgithub.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

@Slf4j
@Controller
@RequestMapping("/oauth2/github")
public class GitHubOauthController {
    // Client ID
    @Value("${oauth2.github.client-id}")
    private String gitHubClientId;
    // Client Secret
    @Value("${oauth2.github.client-secret}")
    private String gitHubClientSecret;
    // GitHub 回调 URL
    @Value("${oauth2.github.redirect-uri}")
    private String gitHubRedirectUri;
    // 重定向到 GitHub 授权页面
    @Value("${oauth2.github.user-authorization-uri}")
    private String gitHubAuthorizationUri;
    // 获取 token
    @Value("${oauth2.github.access-token-uri}")
    private String gitHubAccessTokenUri;
    // 获取用户信息
    @Value("${oauth2.github.user-info-uri}")
    private String gitHubUserInfoUri;
    /**
     * gitHub回调url
     *
     * @param code 授权码
     */
    @ResponseBody
    @GetMapping("/callback")
    public String callback(@RequestParam String code) {
        log.info("code => {}", code);
        String url = MessageFormat.format(gitHubAccessTokenUri, gitHubClientId, gitHubClientSecret, code);
        String token = new RestTemplate().postForObject(url, null, String.class);
        log.info("token => {}", token);
        String resUrl = MessageFormat.format(gitHubUserInfoUri, token);
        String userInfo = new RestTemplate().getForObject(resUrl, String.class);
        log.info("userInfo => {}", userInfo);
        //
        return userInfo;
    }
    /**
     * 重定向到 GitHub 授权页面
     * http://127.0.0.1:5000/heihei/oauth2/github/go
     */
    @GetMapping("/go")
    public void go(HttpServletResponse response) {
        String redirectUrl = MessageFormat.format(gitHubAuthorizationUri, gitHubClientId, gitHubRedirectUri);
        log.info("重定向到 => {}", redirectUrl);
        try {
            response.sendRedirect(redirectUrl);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
