package cn.yuanyu.app.service.sys.impl;

import cn.yuanyu.app.entity.sys.SysUserEntity;
import cn.yuanyu.app.mapper.sys.SysUserMapper;
import cn.yuanyu.app.service.sys.SysUserService;
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
