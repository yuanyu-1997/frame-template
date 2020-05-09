package com.yuanyu.mp.simple.mapper;

import com.yuanyu.mp.simple.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询所有用户
     */
    List<User> findAll();
}
