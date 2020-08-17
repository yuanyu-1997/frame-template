package cn.yuanyu.redisboot.service;

import cn.yuanyu.redisboot.bean.User;
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
        User nai = new User("乔碧萝", "58");
        redisService.set("nai", nai);

        User res = redisService.get("nai");
        System.out.println(res);
    }

}