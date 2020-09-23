package cn.yuanyu.tx.service.t4.impl;

import cn.yuanyu.tx.entity.User;
import cn.yuanyu.tx.mapper.UserMapper;
import cn.yuanyu.tx.service.t4.T5Service2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class T5Service2Inpl implements T5Service2 {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private T5ServiceImpl t5Service;


    // m17不会回滚应为m17新开了一个事务，但是m16会回滚
    @Override
    @Transactional
    public void m16(User a, User b) {
        userMapper.insert(a);
        t5Service.m17(b);
        int he = 1/0;
    }
}
