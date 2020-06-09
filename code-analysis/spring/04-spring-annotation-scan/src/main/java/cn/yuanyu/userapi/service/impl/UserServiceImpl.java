package cn.yuanyu.userapi.service.impl;


import cn.yuanyu.userapi.bean.User;
import cn.yuanyu.userapi.repository.UserRepository;
import cn.yuanyu.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuanyu
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByUsername(String name) {
        return userRepository.findUserByUsername(name);
    }
}
