package cn.yuanyu.uaa.service;

import cn.yuanyu.uaa.model.User;
import cn.yuanyu.uaa.model.bo.UserBo;

import java.util.Map;


public interface UserService {

    /**
     * 注册
     */
    boolean register(User user);

    /**
     * 登录校验
     *
     * @param username 用户名
     * @param password 密码
     */
    Map<String, Object> checkLogin(String username, String password);

    /**
     * 通过用户ID查询用户信息
     *
     * @param userId 用户ID
     */
    User selectByUserId(Integer userId);

    /**
     * 给用户添加角色信息
     *
     * @param userId   用户ID
     * @param roleName 角色名
     */
    void addUserRole(Integer userId, String roleName);

    /**
     * 通过scope查询不同程度的用户信息
     *
     * @param userId 用户ID
     * @param scope  scope
     */
    User selectUserInfoByScope(Integer userId, String scope);

    /**
     * 通过用户ID查询用户所属角色等信息
     *
     * @param userId 用户ID
     */
    UserBo selectUserBoByUserId(Integer userId);
}
