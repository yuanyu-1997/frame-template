package cn.yuanyu.service;


import cn.yuanyu.entity.User;

import java.util.List;

/**
 * @author yuanyu
 */
public interface UserService {
    /**
     * 通过主键id查找用户
     *
     * @param id 用户表主键
     */
    User findById(String id);

    /**
     * 添加用户
     */
    User addUser(User user);

    /**
     * 查询所有用户
     */
    List<User> findAll();
}
