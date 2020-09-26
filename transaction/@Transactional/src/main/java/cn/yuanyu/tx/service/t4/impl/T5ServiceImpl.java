package cn.yuanyu.tx.service.t4.impl;


import cn.yuanyu.tx.entity.User;
import cn.yuanyu.tx.mapper.UserMapper;
import cn.yuanyu.tx.service.t4.T5Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
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
        user.setAge(user.getAge() + 1);
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



    @Override
    @Transactional
    public void m14(User a, User b) {
        userMapper.insert(a);
        m15(b); // 这里依然回滚
        int hehe = 1/0;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW) // TOOD 这个注解是没有效的
    public void m15(User user) {
        userMapper.insert(user);
    }



    // 另外的一个类调用，这个注解生效
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW) // 新开一个事务
    public void m17(User user) {
        userMapper.insert(user);
    }


    // m20 -> m21
    @Override
    @Transactional
    public void m20(User a, User b) {
        userMapper.insert(a);
        try {
            // 调用非核心业务
            m21(b);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
    @Override
    @Transactional // m20 -> m21 调用这个方法的时候，这个注解实际上不生效
    public void m21(User user) {
        userMapper.insert(user);
        int hehe = 1/0;
    }





    // T5Service2.m22 -> T5Service.m23
    @Override
    @Transactional
    public void m23(User user) {
        userMapper.insert(user);
        int hehe = 1/0;
    }


}
