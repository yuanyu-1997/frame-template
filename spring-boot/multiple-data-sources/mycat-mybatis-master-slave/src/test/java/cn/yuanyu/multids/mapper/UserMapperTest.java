package cn.yuanyu.multids.mapper;

import cn.yuanyu.multids.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
    }

    @Test
    public void deleteById() {
        userMapper.deleteById(2L);
    }

    @Test
    public void update() {
        User user = userMapper.queryById(1L);
        System.out.println(user);
        user.setAge(99);
        userMapper.update(user);
    }

    @Test
    public void queryById() {
    }

    @Test
    public void allUser() {
    }
}