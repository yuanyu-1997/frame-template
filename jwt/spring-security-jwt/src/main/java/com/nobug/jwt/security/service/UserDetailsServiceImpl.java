package com.nobug.jwt.security.service;


import com.nobug.jwt.security.entity.UserEntity;
import com.nobug.jwt.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 定义用户信息服务（查询用户信息）
 *
 * @author yuanyu
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user != null) {
            ArrayList<String> permissionList = new ArrayList<>();
            user.getRoles().forEach((role -> role.getPermissions().forEach((permission) -> {
                permissionList.add(permission.getCode());
            })));
            return User.withUsername(user.getUsername())
                    .password(user.getPassword())
                    //权限列表
                    .authorities(permissionList.toArray(new String[0]))
                    .build();
        }
        //如果用户查不到，返回null，由provider来抛出异常
        return null;
    }
}
