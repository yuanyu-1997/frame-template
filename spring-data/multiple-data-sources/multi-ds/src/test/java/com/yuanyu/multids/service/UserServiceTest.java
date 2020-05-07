package com.yuanyu.multids.service;

import com.yuanyu.multids.domain.user.User;
import com.yuanyu.multids.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;


    @Test
    public void testInsert() {
        User user = new User("蔡徐坤", 18, "唱、跳、rap、篮球");
        User ins = userService.insertUser(user);
        System.out.println(ins);
    }
}