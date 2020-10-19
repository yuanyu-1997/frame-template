package cn.yuanyu.app.service.sys;


import cn.yuanyu.app.common.utils.R;

/**
 * @author yuanyu
 */
public interface SysUserTokenService {
    /**
     * 生成token
     * @param userId  用户ID
     */
    R createToken(String userId);

}
