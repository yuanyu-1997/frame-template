package cn.yuanyu.uaa.service.impl;

import cn.yuanyu.uaa.enums.ScopeEnum;
import cn.yuanyu.uaa.mapper.RoleMapper;
import cn.yuanyu.uaa.mapper.UserMapper;
import cn.yuanyu.uaa.mapper.UserRoleMapper;
import cn.yuanyu.uaa.model.Role;
import cn.yuanyu.uaa.model.User;
import cn.yuanyu.uaa.model.UserRole;
import cn.yuanyu.uaa.model.bo.RoleBo;
import cn.yuanyu.uaa.model.bo.UserBo;
import cn.yuanyu.uaa.service.UserService;
import cn.yuanyu.uaa.utils.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 注册
     */
    @Override
    public boolean register(User user) {
        if (StringUtils.isNoneBlank(user.getUsername()) && StringUtils.isNoneBlank(user.getPassword())) {
            Date current = new Date();
            //密码加密存储
            user.setPassword(EncryptUtils.sha256Crypt(user.getPassword(), null));
            user.setCreateTime(current);
            user.setUpdateTime(current);
            user.setStatus(1);
            userMapper.insert(user);
            log.info("用户注册 --> " + user);
            return true;
        }
        return false;
    }

    /**
     * 登录校验
     *
     * @param username 用户名
     * @param password 密码
     */
    @Override
    public Map<String, Object> checkLogin(String username, String password) {
        Map<String, Object> result = new HashMap<>(2);
        log.info(MessageFormat.format("用户登录 --> username:{0},password:{1}", username, password));
        User correctUser = userMapper.selectByUsername(username);
        result.put("result", EncryptUtils.checkSha256Crypt(password, correctUser.getPassword()));
        result.put("user", correctUser);
        return result;
    }

    @Override
    public User selectByUserId(Integer userId) {
        return userMapper.selectById(userId);
    }


    @Override
    public void addUserRole(Integer userId, String roleName) {
        if (userId != null && StringUtils.isNoneBlank(roleName)) {
            //1. 查询角色ID
            Role role = roleMapper.selectByRoleName(roleName);

            if (role != null) {
                UserRole savedUserRole = userRoleMapper.selectByUserIdRoleId(userId, role.getId());

                if (savedUserRole == null) {
                    //2. 给用户添加角色信息
                    UserRole userRole = new UserRole(userId, role.getId());
                    userRoleMapper.insert(userRole);
                }
            }
        }
    }

    @Override
    public User selectUserInfoByScope(Integer userId, String scope) {
        User user = userMapper.selectById(userId);

        //如果是基础权限，则部分信息不返回
        if (ScopeEnum.BASIC.getCode().equalsIgnoreCase(scope)) {
            user.setPassword(null);
            user.setCreateTime(null);
            user.setUpdateTime(null);
            user.setStatus(null);
        }

        return user;
    }

    @Override
    public UserBo selectUserBoByUserId(Integer userId) {
        UserBo result = new UserBo();
        User user = userMapper.selectById(userId);


        if (user != null) {
            BeanUtils.copyProperties(user, result);

            List<UserRole> userRoleList = userRoleMapper.selectByUserId(userId);

            if (userRoleList != null && userRoleList.size() > 0) {
                //查询用户对应的所有角色
                Set<RoleBo> roles = new HashSet<>();

                for (UserRole userRole : userRoleList) {
                    Role role = roleMapper.selectById(userRole.getRoleId());
                    RoleBo roleBo = new RoleBo();
                    BeanUtils.copyProperties(role, roleBo);
                    //可访问的功能权限相关信息在这个demo中就省略了
                    roles.add(roleBo);
                }

                result.setRoles(roles);
            }

        }

        return result;
    }
}
