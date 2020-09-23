package cn.yuanyu.dynamicproxy.service.impl;


import cn.yuanyu.dynamicproxy.bean.User;
import cn.yuanyu.dynamicproxy.service.UserService;

import java.util.HashMap;

public class UserServiceImpl implements UserService {
    private static final HashMap<String, User> userNap = new HashMap<>();

    @Override
    public void addUser(User user) {
        userNap.put(user.getName(), user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userNap.get(username);
    }
}
