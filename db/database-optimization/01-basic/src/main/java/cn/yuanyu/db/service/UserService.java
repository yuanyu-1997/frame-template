package cn.yuanyu.db.service;


import cn.yuanyu.db.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    public void insert(User user) {
        // userMapper.insert(user);
    }

    public void update(User user) {
        // userMapper.update(user);
    }

    public void delete(Integer id) {
        // userMapper.delete(id);
    }

    public User selectOne(Integer id) {
        // User item = userMapper.selectOne(id);
        // return item;
        return new User();
    }

    public List<User> selectList() {
        // List<User> userList = userMapper.selectList();
        // return userList;
        return new ArrayList<>();
    }

}
