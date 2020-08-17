package cn.yuanyu.uaa.mapper;

import cn.yuanyu.uaa.model.AuthAccessToken;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface AuthAccessTokenMapper extends BaseMapper<AuthAccessToken> {

    /**
     * 通过userId + clientId + scope查询记录
     * @param userId 用户ID
     * @param clientId 接入的客户端ID
     * @param scope scope
     */
    AuthAccessToken selectByUserIdClientIdScope(@Param("userId") Integer userId, @Param("clientId") Integer clientId, @Param("scope") String scope);

    /**
     * 通过Access Token查询记录
     * @param accessToken Access Token
     */
    AuthAccessToken selectByAccessToken(@Param("accessToken") String accessToken);
}