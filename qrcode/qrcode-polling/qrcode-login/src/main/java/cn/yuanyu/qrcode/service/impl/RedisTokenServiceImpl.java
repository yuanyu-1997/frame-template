package cn.yuanyu.qrcode.service.impl;


import cn.yuanyu.qrcode.service.ITokenService;
import cn.yuanyu.qrcode.util.RedisKeyBuilder;
import cn.yuanyu.qrcode.vo.AccessToken;
import cn.yuanyu.qrcode.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 访问令牌管理类
 */
@Slf4j
@Service("tokenService")
public class RedisTokenServiceImpl implements ITokenService {

    @Autowired
    private StringRedisServiceImpl redisService;

    @Override
    public Response<AccessToken> createAccessToken(String userId) {
        if (StringUtils.isBlank(userId)) {
            log.error("userId参数不能为空");
            return Response.failResult("userId参数不能为空");
        }
        // 根据实际业务来...
        AccessToken token = new AccessToken();
        String accessToken = UUID.randomUUID().toString();
        String refreshToken = UUID.randomUUID().toString();
        token.setAccessToken(accessToken);
        token.setExpireIn(AccessToken.ACCESSTOKEN_EXPIREIN);
        token.setRefreshToken(refreshToken);
        token.setOpenId(userId);
        // 缓存token与userId的关联关系
        String userIdAccessTokenKey = RedisKeyBuilder.getUserIdAccessTokenKey(userId);
        redisService.cacheValue(userIdAccessTokenKey, accessToken, AccessToken.ACCESSTOKEN_EXPIREIN);
        String accessTokenUserIdKey = RedisKeyBuilder.getAccessTokenUserIdKey(accessToken);
        redisService.cacheValue(accessTokenUserIdKey, userId, AccessToken.ACCESSTOKEN_EXPIREIN);
        return Response.successResult("生成访问令牌成功", token);
    }

}
