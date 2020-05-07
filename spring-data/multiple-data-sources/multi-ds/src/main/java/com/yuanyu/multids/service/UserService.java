package com.yuanyu.multids.service;


import com.yuanyu.multids.domain.user.User;

public interface UserService {
    /**
     * 插入一条用户
     */
    User insertUser(User user);

    /**
     * 通过用户名查找用户
     */
    User findByName(String name);
}
