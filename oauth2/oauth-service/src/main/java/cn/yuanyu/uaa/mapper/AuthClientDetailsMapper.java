package cn.yuanyu.uaa.mapper;

import cn.yuanyu.uaa.model.AuthClientDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface AuthClientDetailsMapper extends BaseMapper<AuthClientDetails> {

    /**
     * 通过clientId查询接入的客户端详情
     */
    AuthClientDetails selectByClientId(@Param("clientId") String clientId);
}