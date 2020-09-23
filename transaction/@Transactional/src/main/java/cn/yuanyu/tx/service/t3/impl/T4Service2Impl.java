package cn.yuanyu.tx.service.t3.impl;

import cn.yuanyu.tx.entity.User;
import cn.yuanyu.tx.mapper.UserMapper;
import cn.yuanyu.tx.service.t3.T4Service2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class T4Service2Impl implements T4Service2 {
    @Resource
    private UserMapper userMapper;

    @Override
    public void m20(User user) {
        handM21(user);
    }

    // 私有方法去掉一个@Transactional修饰的方法
    private void handM21(User user) {
        m21(user);
    }


    @Override
    @Transactional
    public void m21(User user) {
        userMapper.insert(user);
        int a = 1 / 0;
    }


















}
