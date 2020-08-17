package cn.yuanyu.uaa.service;

import cn.yuanyu.uaa.model.AuthAccessToken;
import cn.yuanyu.uaa.model.AuthClientDetails;
import cn.yuanyu.uaa.model.AuthRefreshToken;
import cn.yuanyu.uaa.model.User;

/**
 * 授权相关Service
 */
public interface AuthorizationService {

    /**
     * 注册需要接入的客户端信息
     *
     * @param clientDetails 用户传递进来的关键信息
     */
    boolean register(AuthClientDetails clientDetails);

    /**
     * 通过id查询客户端信息
     *
     * @param id client_id
     */
    AuthClientDetails selectClientDetailsById(Integer id);

    /**
     * 通过client_id查询客户端信息
     *
     * @param clientId client_id
     */
    AuthClientDetails selectClientDetailsByClientId(String clientId);

    /**
     * 通过Access Token查询记录
     *
     * @param accessToken Access Token
     */
    AuthAccessToken selectByAccessToken(String accessToken);

    /**
     * 通过主键查询记录
     */
    AuthAccessToken selectByAccessId(Integer id);

    /**
     * 通过Refresh Token查询记录
     *
     * @param refreshToken Refresh Token
     */
    AuthRefreshToken selectByRefreshToken(String refreshToken);

    /**
     * 保存哪个用户授权哪个接入的客户端哪种访问范围的权限
     *
     * @param userId      用户ID
     * @param clientIdStr 接入的客户端client_id
     * @param scopeStr    可被访问的用户的权限范围，比如：basic、super
     */
    boolean saveAuthClientUser(Integer userId, String clientIdStr, String scopeStr);

    /**
     * 根据clientId、scope以及当前时间戳生成AuthorizationCode（有效期为10分钟）
     *
     * @param clientIdStr 客户端ID
     * @param scopeStr    scope
     * @param user        用户信息
     */
    String createAuthorizationCode(String clientIdStr, String scopeStr, User user);

    /**
     * 生成Access Token
     *
     * @param savedClientDetails 接入的客户端信息
     * @param grantType          授权方式
     * @param scope              允许访问的用户权限范围
     * @param expiresIn          过期时间
     */
    String createAccessToken(User user, AuthClientDetails savedClientDetails, String grantType, String scope, Long expiresIn);


    /**
     * 生成Refresh Token
     *
     * @param user            用户信息
     * @param authAccessToken 生成的Access Token信息
     */
    String createRefreshToken(User user, AuthAccessToken authAccessToken);
}
