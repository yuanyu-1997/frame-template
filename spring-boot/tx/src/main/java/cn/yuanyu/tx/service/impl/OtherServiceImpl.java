package cn.yuanyu.tx.service.impl;

import cn.yuanyu.tx.mapper.UserMapper;
import cn.yuanyu.tx.entity.User;
import cn.yuanyu.tx.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yuanyu
 */
@Service
public class OtherServiceImpl implements OtherService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 事务方法
     */
    @Override
    @Transactional
    public void i(User user) {
        userMapper.update(user);
        throw new RuntimeException("回滚");
    }
}
