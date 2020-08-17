package cn.yuanyu.uaa.service.impl;

import cn.yuanyu.uaa.enums.ExpireEnum;
import cn.yuanyu.uaa.mapper.SsoAccessTokenMapper;
import cn.yuanyu.uaa.mapper.SsoClientDetailsMapper;
import cn.yuanyu.uaa.mapper.SsoRefreshTokenMapper;
import cn.yuanyu.uaa.model.sso.SsoAccessToken;
import cn.yuanyu.uaa.model.sso.SsoClientDetails;
import cn.yuanyu.uaa.model.sso.SsoRefreshToken;
import cn.yuanyu.uaa.model.User;
import cn.yuanyu.uaa.service.SsoService;
import cn.yuanyu.uaa.utils.DateUtils;
import cn.yuanyu.uaa.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class SsoServiceImpl implements SsoService{
    @Autowired
    private SsoAccessTokenMapper ssoAccessTokenMapper;
    @Autowired
    private SsoRefreshTokenMapper ssoRefreshTokenMapper;
    @Autowired
    private SsoClientDetailsMapper ssoClientDetailsMapper;

    @Override
    public SsoClientDetails selectByPrimaryKey(Integer id) {
        return ssoClientDetailsMapper.selectById(id);
    }

    @Override
    public SsoClientDetails selectByRedirectUrl(String redirectUrl) {
        return ssoClientDetailsMapper.selectByRedirectUrl(redirectUrl);
    }

    @Override
    public SsoAccessToken selectByAccessId(Integer id) {
        return ssoAccessTokenMapper.selectById(id);
    }

    @Override
    public SsoAccessToken selectByAccessToken(String accessToken) {
        return ssoAccessTokenMapper.selectByAccessToken(accessToken);
    }

    @Override
    public SsoRefreshToken selectByTokenId(Integer tokenId) {
        return ssoRefreshTokenMapper.selectByTokenId(tokenId);
    }

    @Override
    public SsoRefreshToken selectByRefreshToken(String refreshToken) {
        return ssoRefreshTokenMapper.selectByRefreshToken(refreshToken);
    }


    /**
     * 生成Access Token
     *
     * @param user             用户信息
     * @param expiresIn        过期时间
     * @param ssoClientDetails 接入客户端详情
     * @param requestIP        用户请求的IP
     */
    @Override
    public String createAccessToken(User user, Long expiresIn, String requestIP, SsoClientDetails ssoClientDetails) {
        Date current = new Date();
        //过期的时间戳
        Long expiresAt = DateUtils.nextDaysSecond(ExpireEnum.ACCESS_TOKEN.getTime(), null);

        // 1.拼装待加密字符串（username + 渠道CODE + 当前精确到毫秒的时间戳）
        String str = user.getUsername() + ssoClientDetails.getClientName() + DateUtils.currentTimeMillis();

        // 2.SHA1加密
        String accessTokenStr = "11." + EncryptUtils.sha1Hex(str) + "." + expiresIn + "." + expiresAt;

        // 3.保存Access Token
       SsoAccessToken savedAccessToken = ssoAccessTokenMapper.selectByUserIdAndClientId(user.getId(), ssoClientDetails.getId());
        //如果存在匹配的记录，则更新原记录，否则向数据库中插入新记录
        if(savedAccessToken != null){
            savedAccessToken.setAccessToken(accessTokenStr);
            savedAccessToken.setExpiresIn(expiresAt);
            savedAccessToken.setUpdateUser(user.getId());
            savedAccessToken.setUpdateTime(current);
            ssoAccessTokenMapper.updateById(savedAccessToken);
        }else{
            savedAccessToken = new SsoAccessToken();
            savedAccessToken.setAccessToken(accessTokenStr);
            savedAccessToken.setUserId(user.getId());
            savedAccessToken.setUserName(user.getUsername());
            savedAccessToken.setIp(requestIP);
            savedAccessToken.setClientId(ssoClientDetails.getId());
            savedAccessToken.setChannel(ssoClientDetails.getClientName());
            savedAccessToken.setExpiresIn(expiresAt);
            savedAccessToken.setCreateUser(user.getId());
            savedAccessToken.setUpdateUser(user.getId());
            savedAccessToken.setCreateTime(current);
            savedAccessToken.setUpdateTime(current);
            ssoAccessTokenMapper.insert(savedAccessToken);
        }
        // 4.返回Access Token
        return accessTokenStr;
    }

    /**
     * 生成Refresh Token
     *
     * @param user           用户信息
     * @param ssoAccessToken 生成的 Access Token 信息
     */
    @Override
    public String createRefreshToken(User user, SsoAccessToken ssoAccessToken) {
        Date current = new Date();
        // 过期时间
        Long expiresIn = DateUtils.dayToSecond(ExpireEnum.REFRESH_TOKEN.getTime());
        // 过期的时间戳
        Long expiresAt = DateUtils.nextDaysSecond(ExpireEnum.REFRESH_TOKEN.getTime(), null);
        // 1. 拼装待加密字符串（username + accessToken + 当前精确到毫秒的时间戳）
        String str = user.getUsername() + ssoAccessToken.getAccessToken() + DateUtils.currentTimeMillis();
        // 2. SHA1加密
        String refreshTokenStr = "12." + EncryptUtils.sha1Hex(str) + "." + expiresIn + "." + expiresAt;
        // 3. 保存Refresh Token
        SsoRefreshToken savedRefreshToken = ssoRefreshTokenMapper.selectByTokenId(ssoAccessToken.getId());
        // 如果存在tokenId匹配的记录，则更新原记录，否则向数据库中插入新记录
        if(savedRefreshToken != null){
            savedRefreshToken.setRefreshToken(refreshTokenStr);
            savedRefreshToken.setExpiresIn(expiresAt);
            savedRefreshToken.setUpdateUser(user.getId());
            savedRefreshToken.setUpdateTime(current);
            ssoRefreshTokenMapper.updateById(savedRefreshToken);
        }else{
            savedRefreshToken = new SsoRefreshToken();
            savedRefreshToken.setTokenId(ssoAccessToken.getId());
            savedRefreshToken.setRefreshToken(refreshTokenStr);
            savedRefreshToken.setExpiresIn(expiresAt);
            savedRefreshToken.setCreateUser(user.getId());
            savedRefreshToken.setUpdateUser(user.getId());
            savedRefreshToken.setCreateTime(current);
            savedRefreshToken.setUpdateTime(current);
            ssoRefreshTokenMapper.insert(savedRefreshToken);
        }
        // 4.返回Refresh Tokens
        return refreshTokenStr;
    }

}
