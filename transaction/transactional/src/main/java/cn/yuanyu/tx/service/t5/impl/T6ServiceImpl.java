package cn.yuanyu.tx.service.t5.impl;


import cn.yuanyu.tx.entity.User;
import cn.yuanyu.tx.mapper.UserMapper;
import cn.yuanyu.tx.service.t5.T6Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
public class T6ServiceImpl implements T6Service {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public User m14(String username) {
        User ret = userMapper.queryByUsername(username);

        // java.sql.SQLException: Connection is read-only. Queries leading to data modification are not allowed
        // 执行了修改操作
        ret.setAge(ret.getAge() + 10);
        userMapper.updateByUsername(ret);

        return ret;
    }


    @Override
    @Transactional(readOnly = true)
    public User m15(String username) {
        User ret = userMapper.queryByUsername(username);
        log.info("m15 query user => {}", ret);
        // 给m16去修改用户
        ret.setAge(ret.getAge() + 10);
        m16(ret);
        return ret;
    }


    // Cause: java.sql.SQLException: Connection is read-only. Queries leading to data modification are not allowed
    @Override
    @Transactional // 传播行为
    public void m16(User user) {
        userMapper.updateByUsername(user);
    }



}
