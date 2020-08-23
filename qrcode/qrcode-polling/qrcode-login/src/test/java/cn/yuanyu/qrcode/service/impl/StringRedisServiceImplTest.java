package cn.yuanyu.qrcode.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class StringRedisServiceImplTest {


    @Autowired
    private StringRedisServiceImpl redisService;


    @Test
    public void test() {
        redisService.cacheValue("hoby", "code", 100);
        String hoby = redisService.getValue("hoby");
        System.out.println("res => " + hoby);
    }

}