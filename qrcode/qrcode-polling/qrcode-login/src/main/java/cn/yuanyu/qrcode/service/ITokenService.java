package cn.yuanyu.qrcode.service;


import cn.yuanyu.qrcode.vo.AccessToken;
import cn.yuanyu.qrcode.vo.Response;

import java.io.Serializable;

public interface ITokenService extends Serializable {

    /**
     * 生成访问令牌accessToken
     *
     * @param userId 用户ID
     * @return AccessToken
     */
    Response<AccessToken> createAccessToken(String userId);

}
