package cn.yuanyu.uaa.mapper;

import cn.yuanyu.uaa.model.AuthRefreshToken;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface AuthRefreshTokenMapper extends BaseMapper<AuthRefreshToken> {

    /**
     * 通过tokenId查询记录
     * @param tokenId tokenId
     */
    AuthRefreshToken selectByTokenId(@Param("tokenId") Integer tokenId);

    /**
     * 通过Refresh Token查询记录
     * @param refreshToken Refresh Token
     */
    AuthRefreshToken selectByRefreshToken(@Param("refreshToken") String refreshToken);
}