package cn.yuanyu.log.service.impl;


import cn.yuanyu.log.entity.UserEntity;
import cn.yuanyu.log.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * @author yuanyu
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    //根据 账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userService.queryByUsername(username);
        //模拟
        return User.withUsername(entity.getUsername())
                .password(entity.getPassword())
                .authorities("lord")
                .build();
    }
}
