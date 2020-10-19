package cn.yuanyu.app.service.sys.impl;


import cn.yuanyu.app.entity.sys.SysUserTokenEntity;
import cn.yuanyu.app.mapper.sys.SysUserTokenMapper;
import cn.yuanyu.app.modules.sys.oauth2.TokenGenerator;
import cn.yuanyu.app.service.sys.SysUserTokenService;
import cn.yuanyu.app.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author yuanyu
 */
@Service("sysUserTokenService")
public class SysUserTokenServiceImpl implements SysUserTokenService {

    @Autowired
    private SysUserTokenMapper sysUserTokenMapper;

    //12小时后过期
    private static final int EXPIRE = 3600 * 12;

    @Override
    public R createToken(String userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
        //判断是否生成过token
        SysUserTokenEntity tokenEntity = sysUserTokenMapper.selectById(userId);
        if (tokenEntity == null) {
            tokenEntity = new SysUserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //保存token
            sysUserTokenMapper.insert(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //更新token
            sysUserTokenMapper.updateById(tokenEntity);
        }
        return R.ok().put("token", token).put("expire", EXPIRE);
    }
}
