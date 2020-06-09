package cn.yuanyu.userapi.repository;

import cn.yuanyu.userapi.bean.User;
import org.springframework.stereotype.Repository;

/**
 * @author yuanyu
 */
@Repository
public class UserRepository {

    public User findUserByUsername(String name) {
        return new User(name, 18);
    }
}
