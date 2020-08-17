package cn.yuanyu.uaa.mapper;

import cn.yuanyu.uaa.model.sso.SsoAccessToken;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SsoAccessTokenMapper extends BaseMapper<SsoAccessToken> {


    /**
     * 通过用户ID查询记录
     * @param userId 用户ID
     * @param clientId 请求Token的渠道
     */
    SsoAccessToken selectByUserIdAndClientId(@Param("userId") Integer userId, @Param("clientId") Integer clientId);

    /**
     * 通过Access Token查询记录
     * @param accessToken Access Token
     */
    SsoAccessToken selectByAccessToken(@Param("accessToken") String accessToken);
}