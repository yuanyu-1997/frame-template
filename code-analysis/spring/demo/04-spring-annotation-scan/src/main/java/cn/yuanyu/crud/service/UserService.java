package cn.yuanyu.crud.service;


import cn.yuanyu.crud.bean.User;

/**
 * @author yuanyu
 */
public interface UserService {
    User findUserByUsername(String name);
}
