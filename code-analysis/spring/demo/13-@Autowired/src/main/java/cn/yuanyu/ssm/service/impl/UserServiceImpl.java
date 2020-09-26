package cn.yuanyu.ssm.service.impl;

import cn.yuanyu.ssm.mapper.UserMapper;
import cn.yuanyu.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public void print() {
        System.out.println(userMapper);
    }
}
