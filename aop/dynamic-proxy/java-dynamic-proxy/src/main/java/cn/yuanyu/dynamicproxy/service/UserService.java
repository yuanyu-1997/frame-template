package cn.yuanyu.dynamicproxy.service;


import cn.yuanyu.dynamicproxy.bean.User;

public interface UserService {
    void addUser(User user);
    User getUserByUsername(String username);
}
