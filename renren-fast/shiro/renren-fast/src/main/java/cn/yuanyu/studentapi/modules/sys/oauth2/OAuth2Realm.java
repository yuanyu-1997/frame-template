/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package cn.yuanyu.studentapi.modules.sys.oauth2;

import cn.yuanyu.studentapi.entity.sys.SysPermissionEntity;
import cn.yuanyu.studentapi.entity.sys.SysRoleEntity;
import cn.yuanyu.studentapi.entity.sys.SysUserEntity;
import cn.yuanyu.studentapi.entity.sys.SysUserTokenEntity;
import cn.yuanyu.studentapi.mapper.sys.SysUserMapper;
import cn.yuanyu.studentapi.mapper.sys.SysUserRolePermissionMapper;
import cn.yuanyu.studentapi.mapper.sys.SysUserTokenMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 认证
 *
 * @author yuanyu
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRolePermissionMapper sysUserRolePermissionMapper;

    @Autowired
    private SysUserTokenMapper sysUserTokenMapper;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权 (验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUserEntity user = (SysUserEntity) principals.getPrimaryPrincipal();
        //根据用户名去数据库查询用户信息
        SysUserEntity sysUserEntity = sysUserRolePermissionMapper.getUserDetailByUsername(user.getUsername());
        //添加角色和权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (SysRoleEntity sysRoleEntity : sysUserEntity.getRoles()) {
            //添加角色
            info.addRole(sysRoleEntity.getRoleName());
            //添加权限
            for (SysPermissionEntity sysPermissionEntity : sysRoleEntity.getPermissions()) {
                info.addStringPermission(sysPermissionEntity.getPermTag());
            }
        }
        return info;
    }

    /**
     * 认证 (登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        //根据accessToken，查询用户信息
        SysUserTokenEntity tokenEntity = sysUserTokenMapper.queryByToken(accessToken);
        //token失效
        if (tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        //查询用户信息
        SysUserEntity user = sysUserMapper.selectById(tokenEntity.getUserId());
        //账号锁定
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        return new SimpleAuthenticationInfo(user, accessToken, getName());
    }
}
