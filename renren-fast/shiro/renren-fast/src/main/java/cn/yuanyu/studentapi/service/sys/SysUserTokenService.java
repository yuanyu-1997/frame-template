package cn.yuanyu.studentapi.service.sys;


import cn.yuanyu.studentapi.common.utils.R;

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
