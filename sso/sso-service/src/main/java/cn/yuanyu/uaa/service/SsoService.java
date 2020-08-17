package cn.yuanyu.uaa.service;

import cn.yuanyu.uaa.model.sso.SsoAccessToken;
import cn.yuanyu.uaa.model.sso.SsoClientDetails;
import cn.yuanyu.uaa.model.sso.SsoRefreshToken;
import cn.yuanyu.uaa.model.User;

/**
 * SSO单点登录相关Service
 */
public interface SsoService {

    /**
     * 根据ID查询接入客户端
     *
     * @param id id
     */
    SsoClientDetails selectByPrimaryKey(Integer id);

    /**
     * 根据URL查询记录
     *
     * @param redirectUrl 回调URL
     */
    SsoClientDetails selectByRedirectUrl(String redirectUrl);

    /**
     * 通过主键ID查询记录
     *
     * @param id ID
     */
    SsoAccessToken selectByAccessId(Integer id);

    /**
     * 通过Access Token查询记录
     *
     * @param accessToken Access Token
     */
    SsoAccessToken selectByAccessToken(String accessToken);

    /**
     * 通过tokenId查询记录
     *
     * @param tokenId tokenId
     */
    SsoRefreshToken selectByTokenId(Integer tokenId);

    /**
     * 通过 Refresh Token 查询记录
     *
     * @param refreshToken Refresh Token
     */
    SsoRefreshToken selectByRefreshToken(String refreshToken);

    /**
     * 生成Access Token
     *
     * @param user             用户信息
     * @param expiresIn        过期时间
     * @param ssoClientDetails 接入客户端详情
     * @param requestIP        用户请求的IP
     */
    String createAccessToken(User user, Long expiresIn, String requestIP, SsoClientDetails ssoClientDetails);


    /**
     * 生成Refresh Token
     *
     * @param user           用户信息
     * @param ssoAccessToken 生成的 Access Token 信息
     */
    String createRefreshToken(User user, SsoAccessToken ssoAccessToken);

}
