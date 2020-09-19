package cn.yuanyu.tx.service.t1.impl;

import cn.yuanyu.tx.entity.User;
import cn.yuanyu.tx.mapper.UserMapper;
import cn.yuanyu.tx.service.t1.T1Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class T1ServiceImpl implements T1Service {

    @Resource
    private UserMapper userMapper;

    // 没有事务的方法
    @Override
    public void m1(User user) {
        m2(user);
    }

    // 事务方法
    @Override
    @Transactional
    public void m2(User user) {
        userMapper.insert(user);
        int i = 1 / 0; // 发生异常，但是这里用户是能够插入成功的
    }
}
