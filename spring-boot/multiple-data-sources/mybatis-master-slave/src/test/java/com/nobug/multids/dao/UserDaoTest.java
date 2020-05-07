package com.nobug.multids.dao;

import com.nobug.multids.entity.User;
import com.nobug.multids.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDaoTest {

    @Autowired
    UserMapper userDao;

    @Test
    public void test_insert(){
        User user = new User(284950495, "乔碧萝", "女", new Date(), "北京","123456");
        userDao.insert(user);
    }
}