package cn.yuanyu.service.impl;

import cn.yuanyu.dao.UserMapper;
import cn.yuanyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

}