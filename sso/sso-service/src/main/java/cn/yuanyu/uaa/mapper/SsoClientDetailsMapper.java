package cn.yuanyu.uaa.mapper;

import cn.yuanyu.uaa.model.sso.SsoClientDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SsoClientDetailsMapper extends BaseMapper<SsoClientDetails> {

    /**
     * 根据URL查询记录
     *
     * @param redirectUrl 回调URL
     */
    SsoClientDetails selectByRedirectUrl(@Param("redirectUrl") String redirectUrl);
}