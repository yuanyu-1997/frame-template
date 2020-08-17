package cn.yuanyu.uaa.controller;

import cn.yuanyu.uaa.common.Constants;
import cn.yuanyu.uaa.common.SpringContextUtils;
import cn.yuanyu.uaa.enums.ErrorCodeEnum;
import cn.yuanyu.uaa.enums.ExpireEnum;
import cn.yuanyu.uaa.model.User;
import cn.yuanyu.uaa.model.bo.UserBo;
import cn.yuanyu.uaa.model.sso.SsoAccessToken;
import cn.yuanyu.uaa.model.sso.SsoClientDetails;
import cn.yuanyu.uaa.model.sso.SsoRefreshToken;
import cn.yuanyu.uaa.service.SsoService;
import cn.yuanyu.uaa.service.UserService;
import cn.yuanyu.uaa.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * SSO单点登录相关接口
 */
@Slf4j
@Controller
@RequestMapping("/sso")
public class SsoController {

    @Autowired
    private SsoService ssoService;

    @Autowired
    private UserService userService;

    /**
     * 获取Token
     *
     * @param redirectUri 回调URL
     */
    // http://localhost:8000/uaa/sso/token?redirect_uri=www.baidu.com
    @RequestMapping("/token")
    public ModelAndView authorize(@RequestParam("redirect_uri") String redirectUri, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        //过期时间
        Long expiresIn = DateUtils.dayToSecond(ExpireEnum.ACCESS_TOKEN.getTime());
        //查询接入客户端
        SsoClientDetails ssoClientDetails = ssoService.selectByRedirectUrl(redirectUri);
        //获取用户IP
        String requestIp = SpringContextUtils.getRequestIp(request);
        //生成 Access Token
        String accessTokenStr = ssoService.createAccessToken(user, expiresIn, requestIp, ssoClientDetails);
        //查询已经插入到数据库的 Access Token
        SsoAccessToken ssoAccessToken = ssoService.selectByAccessToken(accessTokenStr);
        //生成 Refresh Token
        String refreshTokenStr = ssoService.createRefreshToken(user, ssoAccessToken);

        log.info("单点登录获取token, user => {}", user);
        String params = "?code=" + accessTokenStr;
        return new ModelAndView("redirect:" + redirectUri + params);
    }


    /**
     * 校验 access token，并返回用户信息
     *
     * @param accessToken access token
     */
    // http://localhost:8000/uaa/sso/verify?access_token=11.6ada443a4cab0847078a8c95c1d154273eacff72.2592000.1600013897
    @ResponseBody
    @RequestMapping(value = "/verify")
    public Map<String, Object> verify(@RequestParam(value = "access_token") String accessToken) {
        Map<String, Object> result = new HashMap<>(8);
        try {
            //过期时间
            Long expiresIn = DateUtils.dayToSecond(ExpireEnum.ACCESS_TOKEN.getTime());
            //查询 Access Token
            SsoAccessToken ssoAccessToken = ssoService.selectByAccessToken(accessToken);
            //查询 Refresh Token
            SsoRefreshToken ssoRefreshToken = ssoService.selectByTokenId(ssoAccessToken.getId());
            //查询用户信息
            UserBo userBo = userService.selectUserBoByUserId(ssoAccessToken.getUserId());
            //组装返回信息
            result.put("access_token", ssoAccessToken.getAccessToken());
            result.put("refresh_token", ssoRefreshToken.getRefreshToken());
            result.put("expires_in", expiresIn);
            result.put("user_info", userBo);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            this.generateErrorResponse(result, ErrorCodeEnum.UNKNOWN_ERROR);
            return result;
        }
    }

    /**
     * 通过 Refresh Token 刷新 Access Token
     */
    // http://localhost:8000/uaa/sso/verify?refresh_token=
    @ResponseBody
    @RequestMapping(value = "/refreshToken")
    public Map<String, Object> refreshToken(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>(8);
        //获取 Refresh Token
        String refreshTokenStr = request.getParameter("refresh_token");
        //获取用户IP
        String requestIp = SpringContextUtils.getRequestIp(request);
        try {
            SsoRefreshToken ssoRefreshToken = ssoService.selectByRefreshToken(refreshTokenStr);
            if (ssoRefreshToken != null) {
                Long savedExpiresAt = ssoRefreshToken.getExpiresIn();
                // 过期日期
                LocalDateTime expiresDateTime = DateUtils.ofEpochSecond(savedExpiresAt, null);
                // 当前日期
                LocalDateTime nowDateTime = DateUtils.now();
                // 如果 Refresh Token 已经失效，则需要重新生成
                if (expiresDateTime.isBefore(nowDateTime)) {
                    generateErrorResponse(result, ErrorCodeEnum.EXPIRED_TOKEN);
                    return result;
                } else {
                    // 获取存储的 Access Token
                    SsoAccessToken ssoAccessToken = ssoService.selectByAccessId(ssoRefreshToken.getTokenId());
                    // 查询接入客户端
                    SsoClientDetails ssoClientDetails = ssoService.selectByPrimaryKey(ssoAccessToken.getClientId());
                    // 获取对应的用户信息
                    User user = userService.selectByUserId(ssoAccessToken.getUserId());
                    // 新的过期时间
                    Long expiresIn = DateUtils.dayToSecond(ExpireEnum.ACCESS_TOKEN.getTime());
                    // 生成新的Access Token
                    String newAccessTokenStr = ssoService.createAccessToken(user, expiresIn, requestIp, ssoClientDetails);
                    // 查询用户信息
                    UserBo userBo = userService.selectUserBoByUserId(ssoAccessToken.getUserId());
                    log.info(MessageFormat.format("单点登录重新刷新Token：username:【{0}】,requestIp:【{1}】,old token:【{2}】,new token:【{3}】", user.getUsername(), requestIp, ssoAccessToken.getAccessToken(), newAccessTokenStr));
                    // 组装返回信息
                    result.put("access_token", newAccessTokenStr);
                    result.put("refresh_token", ssoRefreshToken.getRefreshToken());
                    result.put("expires_in", expiresIn);
                    result.put("user_info", userBo);
                    return result;
                }
            } else {
                this.generateErrorResponse(result, ErrorCodeEnum.INVALID_GRANT);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
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
