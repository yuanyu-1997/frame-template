package cn.yuanyu.uaa.controller;

import cn.yuanyu.uaa.common.Constants;
import cn.yuanyu.uaa.enums.ErrorCodeEnum;
import cn.yuanyu.uaa.enums.ExpireEnum;
import cn.yuanyu.uaa.enums.GrantTypeEnum;
import cn.yuanyu.uaa.model.AuthAccessToken;
import cn.yuanyu.uaa.model.AuthClientDetails;
import cn.yuanyu.uaa.model.AuthRefreshToken;
import cn.yuanyu.uaa.model.User;
import cn.yuanyu.uaa.service.AuthorizationService;
import cn.yuanyu.uaa.service.RedisService;
import cn.yuanyu.uaa.service.UserService;
import cn.yuanyu.uaa.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于 oauth2 相关的授权相关操作
 */
@Slf4j
@Controller
@RequestMapping("/oauth2")
public class OauthController {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    /**
     * 接入的客户端需要注册信息（不是谁都可以的，真实情况这里需要审核）
     */
    @ResponseBody
    @PostMapping(value = "/clientRegister")
    public Map<String, Object> clientRegister(@RequestBody AuthClientDetails clientDetails) {
        Map<String, Object> result = new HashMap<>(2);
        boolean registerRet = authorizationService.register(clientDetails);
        if (registerRet) {
            result.put("code", "200");
        } else {
            result.put("code", "500");
            result.put("msg", "注册失败");
        }
        return result;
    }

    /**
     * 授权页面
     *
     * @param responseType 授权类型
     * @param redirectUrl  在页面同意授权后的回调地址
     * @param clientId     客户端ID
     * @param scope        申请的权限范围
     */
    // http://127.0.0.1:8000/uaa/oauth2/authorizePage
    @RequestMapping("/authorizePage")
    public ModelAndView authorizePage(
                                      //@RequestParam("response_type") String responseType,
                                      @RequestParam("client_id") String clientId,
                                      @RequestParam("redirect_uri") String redirectUrl,
                                      @RequestParam("scope") String scope, HttpSession session) {
        log.info("授权页面...");
        ModelAndView modelAndView = new ModelAndView("authorize");
        if (StringUtils.isNoneBlank(redirectUrl)) {
            // 将回调地址添加到session中
            session.setAttribute(Constants.SESSION_AUTH_REDIRECT_URL, redirectUrl);
        }
        // 查询请求授权的客户端信息
        AuthClientDetails clientDetails = authorizationService.selectClientDetailsByClientId(clientId);
        modelAndView.addObject("clientId", clientId);
        modelAndView.addObject("clientName", clientDetails.getClientName());
        modelAndView.addObject("scope", scope);
        log.info("授权的客户端信息 => {}", clientDetails);
        return modelAndView;
    }

    /**
     * 同意授权
     *
     * @param clientId 客户端ID
     * @param scope    权限范围
     */
    @ResponseBody
    @PostMapping(value = "/agree")
    public Map<String, Object> agree(@RequestParam("client_id") String clientId,
                                     @RequestParam("scope") String scope, HttpSession session) {
        log.info("同意授权...");
        Map<String, Object> result = new HashMap<>(2);
        if (StringUtils.isNoneBlank(clientId) && StringUtils.isNoneBlank(scope)) {
            User user = (User) session.getAttribute(Constants.SESSION_USER);
            //1. 向数据库中保存授权信息
            boolean saveFlag = authorizationService.saveAuthClientUser(user.getId(), clientId, scope);
            //2. 返回给页面的数据
            if (saveFlag) {
                result.put("code", 200);
                //授权成功之后的回调地址
                String redirectUrl = (String) session.getAttribute(Constants.SESSION_AUTH_REDIRECT_URL);
                session.removeAttribute(Constants.SESSION_AUTH_REDIRECT_URL);
                if (StringUtils.isNoneBlank(redirectUrl)) {
                    result.put("redirect_uri", redirectUrl);
                }
                log.info("授权成功之后的回调地址 => {}", redirectUrl);








            } else {
                log.info("授权失败");
                result.put("msg", "授权失败！");
            }
        } else {
            log.info("请求参数为空");
            result.put("msg", "请求参数不能为空！");
        }
        return result;
    }

    //
    /**
     * 获取 Authorization Code
     *
     * @param clientId 客户端ID
     * @param scope    权限范围
     * @param redirectUri 回调URL
     * @param status      status，用于防止CSRF攻击（非必填）
     */
    @RequestMapping("/authorize")
    public ModelAndView authorize(@RequestParam("client_id") String clientId,
                                  @RequestParam("scope") String scope,
                                  @RequestParam("redirect_uri") String redirectUri,
                                  @RequestParam(value = "status", required = false) String status,
                                  HttpSession session) {
        log.info("获取 Authorization Code ...");
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        //生成 Authorization Code
        String authorizationCode = authorizationService.createAuthorizationCode(clientId, scope, user);
        String params = "?code=" + authorizationCode;
        if (StringUtils.isNoneBlank(status)) {
            params = params + "&status=" + status;
        }
        log.info("Authorization Code => {}", authorizationCode);
        return new ModelAndView("redirect:" + redirectUri + params);
    }

    /**
     * 通过 Authorization Code 获取 Access Token
     *
     * @param grantType    授权方式
     * @param code         前面获取的 Authorization Code
     * @param clientId  客户端ID
     * @param clientSecret 接入的客户端的密钥
     * @param redirectUri  回调URL
     */
    @ResponseBody
    @RequestMapping(value = "/token")
    public Map<String, Object> token(@RequestParam("grant_type") String grantType,
                                     @RequestParam("code") String code,
                                     @RequestParam("client_id") String clientId,
                                     @RequestParam("client_secret") String clientSecret,
                                     @RequestParam("redirect_uri") String redirectUri) {
        log.info("通过 Authorization Code 获取 Access Token ...");
        Map<String, Object> result = new HashMap<>(8);
        // 校验授权方式
        if (!GrantTypeEnum.AUTHORIZATION_CODE.getType().equals(grantType)) {
            this.generateErrorResponse(result, ErrorCodeEnum.UNSUPPORTED_GRANT_TYPE);
            return result;
        }
        try {
            AuthClientDetails savedClientDetails = authorizationService.selectClientDetailsByClientId(clientId);
            // 校验请求的客户端秘钥和已保存的秘钥是否匹配
            if (!(savedClientDetails != null && savedClientDetails.getClientSecret().equals(clientSecret))) {
                this.generateErrorResponse(result, ErrorCodeEnum.INVALID_CLIENT);
                return result;
            }
            // 校验回调URL
            if (!savedClientDetails.getRedirectUri().equals(redirectUri)) {
                this.generateErrorResponse(result, ErrorCodeEnum.REDIRECT_URI_MISMATCH);
                return result;
            }
            // 从Redis获取允许访问的用户权限范围
            String scope = redisService.get(code + ":scope");
            // 从Redis获取对应的用户信息
            User user = redisService.get(code + ":user");
            // 如果能够通过 Authorization Code 获取到对应的用户信息，则说明该 Authorization Code 有效
            if (StringUtils.isNoneBlank(scope) && user != null) {
                // 过期时间
                Long expiresIn = DateUtils.dayToSecond(ExpireEnum.ACCESS_TOKEN.getTime());
                // 生成 Access Token
                String accessTokenStr = authorizationService.createAccessToken(user, savedClientDetails, grantType, scope, expiresIn);
                // 查询已经插入到数据库的Access Token
                AuthAccessToken authAccessToken = authorizationService.selectByAccessToken(accessTokenStr);
                // 生成Refresh Token
                String refreshTokenStr = authorizationService.createRefreshToken(user, authAccessToken);
                // 返回数据
                result.put("access_token", authAccessToken.getAccessToken());
                result.put("refresh_token", refreshTokenStr);
                result.put("expires_in", expiresIn);
                result.put("scope", scope);
                log.info("Access Token => {}", authAccessToken.getAccessToken());
                return result;
            } else {
                this.generateErrorResponse(result, ErrorCodeEnum.INVALID_GRANT);
                return result;
            }
        } catch (Exception e) {
            this.generateErrorResponse(result, ErrorCodeEnum.UNKNOWN_ERROR);
            return result;
        }
    }

    /**
     * 通过 Refresh Token 刷新 Access Token
     *
     * @param refreshTokenStr Refresh Token
     */
    @ResponseBody
    @RequestMapping(value = "/refreshToken")
    public Map<String, Object> refreshToken(@RequestParam("refresh_token") String refreshTokenStr) {
        log.info("通过 Refresh Token 刷新 Access Token ...");
        Map<String, Object> result = new HashMap<>(8);
        try {
            AuthRefreshToken authRefreshToken = authorizationService.selectByRefreshToken(refreshTokenStr);
            if (authRefreshToken != null) {
                Long savedExpiresAt = authRefreshToken.getExpiresIn();
                //过期日期
                LocalDateTime expiresDateTime = DateUtils.ofEpochSecond(savedExpiresAt, null);
                //当前日期
                LocalDateTime nowDateTime = DateUtils.now();
                //如果Refresh Token已经失效，则需要重新生成
                if (expiresDateTime.isBefore(nowDateTime)) {
                    this.generateErrorResponse(result, ErrorCodeEnum.EXPIRED_TOKEN);
                    return result;
                } else {
                    //获取存储的Access Token
                    AuthAccessToken authAccessToken = authorizationService.selectByAccessId(authRefreshToken.getTokenId());
                    //获取对应的客户端信息
                    AuthClientDetails savedClientDetails = authorizationService.selectClientDetailsById(authAccessToken.getClientId());
                    //获取对应的用户信息
                    User user = userService.selectByUserId(authAccessToken.getUserId());

                    //新的过期时间
                    Long expiresIn = DateUtils.dayToSecond(ExpireEnum.ACCESS_TOKEN.getTime());
                    //生成新的Access Token
                    String newAccessTokenStr = authorizationService.createAccessToken(user, savedClientDetails, authAccessToken.getGrantType(), authAccessToken.getScope(), expiresIn);
                    //返回数据
                    result.put("access_token", newAccessTokenStr);
                    result.put("refresh_token", refreshTokenStr);
                    result.put("expires_in", expiresIn);
                    result.put("scope", authAccessToken.getScope());
                    log.info("Access Token => {}", newAccessTokenStr);
                    return result;
                }
            } else {
                this.generateErrorResponse(result, ErrorCodeEnum.INVALID_GRANT);
                return result;
            }
        } catch (Exception e) {
            this.generateErrorResponse(result, ErrorCodeEnum.UNKNOWN_ERROR);
            return result;
        }
    }

    /**
     * 组装错误请求的返回
     */
    private void generateErrorResponse(Map<String, Object> result, ErrorCodeEnum errorCodeEnum) {
        result.put("error", errorCodeEnum.getError());
        result.put("error_description", errorCodeEnum.getErrorDescription());
    }

}
