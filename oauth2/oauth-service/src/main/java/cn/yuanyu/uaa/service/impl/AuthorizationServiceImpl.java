package cn.yuanyu.uaa.service.impl;

import cn.yuanyu.uaa.enums.ExpireEnum;
import cn.yuanyu.uaa.mapper.*;
import cn.yuanyu.uaa.model.*;
import cn.yuanyu.uaa.service.AuthorizationService;
import cn.yuanyu.uaa.service.RedisService;
import cn.yuanyu.uaa.utils.DateUtils;
import cn.yuanyu.uaa.utils.EncryptUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private AuthClientDetailsMapper authClientDetailsMapper;

    @Autowired
    private AuthScopeMapper authScopeMapper;

    @Autowired
    private AuthClientUserMapper authClientUserMapper;

    @Autowired
    private AuthAccessTokenMapper authAccessTokenMapper;

    @Autowired
    private AuthRefreshTokenMapper authRefreshTokenMapper;

    /**
     * 注册需要接入的客户端信息
     */
    @Override
    public boolean register(AuthClientDetails clientDetails) {
        // 客户端的名称和回调地址不能为空
        if (StringUtils.isNoneBlank(clientDetails.getClientName()) && StringUtils.isNoneBlank(clientDetails.getRedirectUri())) {
            // 生成24位随机的 clientId
            String clientId = EncryptUtils.getRandomStr1(24);
            AuthClientDetails savedClientDetails = authClientDetailsMapper.selectByClientId(clientId);
            // 生成的 clientId 必须是唯一的
            for (int i = 0; i < 10; i++) {
                if (savedClientDetails == null) {
                    break;
                } else {
                    clientId = EncryptUtils.getRandomStr1(24);
                    savedClientDetails = authClientDetailsMapper.selectByClientId(clientId);
                }
            }
            // 生成32位随机的clientSecret
            String clientSecret = EncryptUtils.getRandomStr1(32);
            Date current = new Date();
            //HttpSession session = SpringContextUtils.getSession();
            //User user = (User) session.getAttribute(Constants.SESSION_USER);
            clientDetails.setClientId(clientId);
            clientDetails.setClientSecret(clientSecret);
            //clientDetails.setCreateUser(user.getId());
            clientDetails.setCreateTime(current);
            //clientDetails.setUpdateUser(user.getId());
            clientDetails.setUpdateTime(current);
            clientDetails.setStatus(1);
            // 保存到数据库
            authClientDetailsMapper.insert(clientDetails);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public AuthClientDetails selectClientDetailsById(Integer id) {
        return authClientDetailsMapper.selectById(id);
    }

    @Override
    public AuthClientDetails selectClientDetailsByClientId(String clientId) {
        return authClientDetailsMapper.selectByClientId(clientId);
    }

    @Override
    public AuthAccessToken selectByAccessToken(String accessToken) {
        return authAccessTokenMapper.selectByAccessToken(accessToken);
    }

    @Override
    public AuthAccessToken selectByAccessId(Integer id) {
        return authAccessTokenMapper.selectById(id);
    }

    @Override
    public AuthRefreshToken selectByRefreshToken(String refreshToken) {
        return authRefreshTokenMapper.selectByRefreshToken(refreshToken);
    }

    @Override
    public boolean saveAuthClientUser(Integer userId, String clientId, String scope) {
        AuthClientDetails clientDetails = authClientDetailsMapper.selectByClientId(clientId);
        AuthScope dbScope = authScopeMapper.selectByScopeName(scope);
        if (clientDetails != null && dbScope != null) {
            AuthClientUser clientUser = authClientUserMapper.selectByClientId(clientDetails.getId(), userId, dbScope.getId());
            //如果数据库中不存在记录，则插入
            if (clientUser == null) {
                clientUser = new AuthClientUser();
                clientUser.setUserId(userId);
                clientUser.setAuthClientId(clientDetails.getId());
                clientUser.setAuthScopeId(dbScope.getId());
                authClientUserMapper.insert(clientUser);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String createAuthorizationCode(String clientIdStr, String scopeStr, User user) {
        // 1.拼装待加密字符串（clientId + scope + 当前精确到毫秒的时间戳）
        String str = clientIdStr + scopeStr + DateUtils.currentTimeMillis();
        // 2.SHA1加密
        String encryptedStr = EncryptUtils.sha1Hex(str);
        // 3.1保存本次请求的授权范围
        redisService.setWithExpire(encryptedStr + ":scope", scopeStr, (ExpireEnum.AUTHORIZATION_CODE.getTime()), ExpireEnum.AUTHORIZATION_CODE.getTimeUnit());
        // 3.2保存本次请求所属的用户信息
        redisService.setWithExpire(encryptedStr + ":user", user, (ExpireEnum.AUTHORIZATION_CODE.getTime()), ExpireEnum.AUTHORIZATION_CODE.getTimeUnit());
        // 4.返回 Authorization Code
        return encryptedStr;
    }

    @Override
    public String createAccessToken(User user, AuthClientDetails savedClientDetails, String grantType, String scope, Long expiresIn) {
        Date current = new Date();
        // 过期的时间戳
        Long expiresAt = DateUtils.nextDaysSecond(ExpireEnum.ACCESS_TOKEN.getTime(), null);
        // 1.拼装待加密字符串（username + clientId + 当前精确到毫秒的时间戳）
        String str = user.getUsername() + savedClientDetails.getClientId() + DateUtils.currentTimeMillis();
        // 2.SHA1加密
        String accessTokenStr = "1." + EncryptUtils.sha1Hex(str) + "." + expiresIn + "." + expiresAt;
        // 3.保存 Access Token
        AuthAccessToken authAccessToken = authAccessTokenMapper.selectByUserIdClientIdScope(user.getId(), savedClientDetails.getId(), scope);
        // 如果存在 userId + clientId + scope 匹配的记录，则更新原记录，否则向数据库中插入新记录
        if (authAccessToken != null) {
            authAccessToken.setAccessToken(accessTokenStr);
            authAccessToken.setExpiresIn(expiresAt);
            authAccessToken.setUpdateUser(user.getId());
            authAccessToken.setUpdateTime(current);
            authAccessTokenMapper.updateById(authAccessToken);
        } else {
            authAccessToken = new AuthAccessToken();
            authAccessToken.setAccessToken(accessTokenStr);
            authAccessToken.setUserId(user.getId());
            authAccessToken.setUserName(user.getUsername());
            authAccessToken.setClientId(savedClientDetails.getId());
            authAccessToken.setExpiresIn(expiresAt);
            authAccessToken.setScope(scope);
            authAccessToken.setGrantType(grantType);
            authAccessToken.setCreateUser(user.getId());
            authAccessToken.setUpdateUser(user.getId());
            authAccessToken.setCreateTime(current);
            authAccessToken.setUpdateTime(current);
            authAccessTokenMapper.insert(authAccessToken);
        }
        // 4.返回Access Token
        return accessTokenStr;
    }

    @Override
    public String createRefreshToken(User user, AuthAccessToken authAccessToken) {
        Date current = new Date();
        // 过期时间
        Long expiresIn = DateUtils.dayToSecond(ExpireEnum.REFRESH_TOKEN.getTime());
        // 过期的时间戳
        Long expiresAt = DateUtils.nextDaysSecond(ExpireEnum.REFRESH_TOKEN.getTime(), null);
        // 1.拼装待加密字符串（username + accessToken + 当前精确到毫秒的时间戳）
        String str = user.getUsername() + authAccessToken.getAccessToken() + String.valueOf(DateUtils.currentTimeMillis());
        // 2.SHA1加密
        String refreshTokenStr = "2." + EncryptUtils.sha1Hex(str) + "." + expiresIn + "." + expiresAt;
        // 3.保存Refresh Token
        AuthRefreshToken savedRefreshToken = authRefreshTokenMapper.selectByTokenId(authAccessToken.getId());
        // 如果存在tokenId匹配的记录，则更新原记录，否则向数据库中插入新记录
        if (savedRefreshToken != null) {
            savedRefreshToken.setRefreshToken(refreshTokenStr);
            savedRefreshToken.setExpiresIn(expiresAt);
            savedRefreshToken.setUpdateUser(user.getId());
            savedRefreshToken.setUpdateTime(current);
            authRefreshTokenMapper.updateById(savedRefreshToken);
        } else {
            savedRefreshToken = new AuthRefreshToken();
            savedRefreshToken.setTokenId(authAccessToken.getId());
            savedRefreshToken.setRefreshToken(refreshTokenStr);
            savedRefreshToken.setExpiresIn(expiresAt);
            savedRefreshToken.setCreateUser(user.getId());
            savedRefreshToken.setUpdateUser(user.getId());
            savedRefreshToken.setCreateTime(current);
            savedRefreshToken.setUpdateTime(current);
            authRefreshTokenMapper.insert(savedRefreshToken);
        }
        // 4.返回Refresh Token
        return refreshTokenStr;
    }

}
