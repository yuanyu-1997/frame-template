package cn.yuanyu.studentapi.mapper.sys;


import cn.yuanyu.studentapi.entity.sys.SysUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 操作用户
 *
 * @author yuanyu
 */
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
    /**
     * 通过用户名查找用户
     *
     * @param username 用户名（唯一）
     */
    SysUserEntity selectUserByUsername(String username);
}
