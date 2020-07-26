package cn.yuanyu.studentapi.service.sys.impl;

import cn.yuanyu.studentapi.entity.sys.SysUserEntity;
import cn.yuanyu.studentapi.mapper.sys.SysUserMapper;
import cn.yuanyu.studentapi.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuanyu
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    public SysUserMapper sysUserMapper;

    /**
     * 根据用户名，查询系统用户
     */
    @Override
    public SysUserEntity queryByUserName(String username) {
        return sysUserMapper.selectUserByUsername(username);
    }
}
