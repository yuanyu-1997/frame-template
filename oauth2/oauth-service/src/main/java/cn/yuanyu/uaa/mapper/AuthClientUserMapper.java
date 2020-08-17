package cn.yuanyu.uaa.mapper;

import cn.yuanyu.uaa.model.AuthClientUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface AuthClientUserMapper extends BaseMapper<AuthClientUser> {

    /**
     * 根据 clientId、userId、scopeId查询用户给某个接入客户端的授权信息
     * @param clientId 接入的客户端ID
     * @param userId 用户ID
     */
    AuthClientUser selectByClientId(@Param("clientId") Integer clientId, @Param("userId") Integer userId, @Param("scopeId") Integer scopeId);
}