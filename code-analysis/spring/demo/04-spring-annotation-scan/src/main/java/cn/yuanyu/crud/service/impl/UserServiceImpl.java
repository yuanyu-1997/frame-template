package cn.yuanyu.crud.service.impl;


import cn.yuanyu.crud.bean.User;
import cn.yuanyu.crud.repository.UserRepository;
import cn.yuanyu.crud.service.UserService;
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
