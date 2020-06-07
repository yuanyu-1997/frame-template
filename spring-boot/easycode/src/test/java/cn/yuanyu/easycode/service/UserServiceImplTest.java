package cn.yuanyu.easycode.service;

import cn.yuanyu.easycode.entity.User;
import cn.yuanyu.easycode.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    public void insert() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void update() {
        User user = userService.queryById(1L);
        System.out.println(user);
        user.setAge(user.getAge() + 1);
        userService.update(user);
    }

    @Test
    public void queryById() {
    }

    @Test
    public void queryAllByLimit() {
    }

    @Test
    public void allUser() {
    }
}