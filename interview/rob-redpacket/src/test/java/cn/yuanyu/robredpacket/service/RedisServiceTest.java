package cn.yuanyu.robredpacket.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;


    @Test
    public void test_() {
        redisService.set("key", "value");
        String value = redisService.get("key");
        log.info("value => {}", value);
    }

    @Test
    public void test_2() {
        String value = redisService.get("hehe");
        log.info("value => {}", value);
    }
}