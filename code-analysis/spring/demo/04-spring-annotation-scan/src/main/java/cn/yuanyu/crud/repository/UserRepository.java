package cn.yuanyu.crud.repository;

import cn.yuanyu.crud.bean.User;
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
