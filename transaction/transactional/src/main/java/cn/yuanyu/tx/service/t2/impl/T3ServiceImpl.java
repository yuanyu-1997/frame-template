package cn.yuanyu.tx.service.t2.impl;

import cn.yuanyu.tx.entity.User;
import cn.yuanyu.tx.mapper.UserMapper;
import cn.yuanyu.tx.service.t2.T3Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class T3ServiceImpl implements T3Service {

    @Resource
    private UserMapper userMapper;

    // 事务方法
    @Override
    @Transactional
    public void m4(User user) {
        userMapper.insert(user);
        int i = 1 / 0; // 发生异常，正常回滚
    }
}
