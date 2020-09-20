package cn.yuanyu.tx.service.t4.impl;


import cn.yuanyu.tx.entity.User;
import cn.yuanyu.tx.mapper.UserMapper;
import cn.yuanyu.tx.service.t4.T5Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class T5ServiceImpl implements T5Service {
    @Resource
    private UserMapper userMapper;

    /**
     * Error 也是会回滚的
     */
    @Override
    @Transactional
    public void m11(User user) {
        user.setAge(user.getAge()+1);
        if (user.getAge() == 100) {
            userMapper.insert(user);
        }
        m11(user);
    }

    /**
     * IOException默认不会回滚
     */
    @Override
    @Transactional
    public void m12(User user) throws IOException {
        userMapper.insert(user);
        throw new IOException("IOException默认不会回滚");
    }

    /**
     * 设置IOException回滚
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void m13(User user) throws IOException {
        userMapper.insert(user);
        throw new IOException("设置IOException回滚");
    }
}
