package cn.yuanyu.uaa.service;

import cn.yuanyu.uaa.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    //TODO 1.注册用户
    @Test
    public void insert(){
        User user = new User("zhangsan","123456","17783649163","zhangsan@qq.com");
        userService.register(user);

    }
}