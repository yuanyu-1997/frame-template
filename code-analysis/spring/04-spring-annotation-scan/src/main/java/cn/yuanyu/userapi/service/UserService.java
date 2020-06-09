package cn.yuanyu.userapi.service;


import cn.yuanyu.userapi.bean.User;

/**
 * @author yuanyu
 */
public interface UserService {
    User findUserByUsername(String name);
}
