package com.yuanyu.multids.service.impl;

import com.yuanyu.multids.aop.datasource.DataSourceName;
import com.yuanyu.multids.aop.datasource.TargetDataSource;
import com.yuanyu.multids.domain.user.User;
import com.yuanyu.multids.repository.user.UserRepository;
import com.yuanyu.multids.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    @TargetDataSource(name = DataSourceName.USER)
    public User insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @TargetDataSource(name = DataSourceName.USER)
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
