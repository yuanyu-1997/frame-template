package cn.yuanyu.cache.service.impl;

import cn.yuanyu.cache.entity.User;
import cn.yuanyu.cache.mapper.UserMapper;
import cn.yuanyu.cache.service.UserService;
import cn.yuanyu.cache.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author yuanyu
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 通过ID查询单条数据
     */
    @Override
    public User queryById(Long id) {
        User cache = redisUtils.get("user:id", User.class);
        if (cache != null) {
            return cache;
        }
        User db = userMapper.selectById(id);
        redisUtils.set("user:id", db);
        return db;
    }

}