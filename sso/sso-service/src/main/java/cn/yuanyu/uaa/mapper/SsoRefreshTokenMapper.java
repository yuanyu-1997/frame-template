package cn.yuanyu.uaa.mapper;

import cn.yuanyu.uaa.model.sso.SsoRefreshToken;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SsoRefreshTokenMapper extends BaseMapper<SsoRefreshToken> {

    /**
     * 通过tokenId查询记录
     * @param tokenId tokenId
     */
    SsoRefreshToken selectByTokenId(@Param("tokenId") Integer tokenId);

    /**
     * 通过Refresh Token查询记录
     * @param refreshToken Refresh Token
     */
    SsoRefreshToken selectByRefreshToken(@Param("refreshToken") String refreshToken);
}