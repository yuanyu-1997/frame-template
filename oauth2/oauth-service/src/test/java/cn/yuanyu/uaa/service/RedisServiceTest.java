package cn.yuanyu.uaa.service;

import cn.yuanyu.uaa.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void test_(){
        User user = new User("乔碧萝","123456","17783649163","nainai@qq.com");
        redisService.set("k", user);

        User v = redisService.get("k");
        System.out.println(v);

    }
}