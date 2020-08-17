package cn.yuanyu.redisboot.redistest;

import cn.yuanyu.redisboot.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MyRedisTemplateTest {

//    @Autowired
//    private RedisTemplate<String, User> redisTemplate;
//    @Test
//    public void stringRedisTemplate() {
//        // 对象
//        User user = new User("李四", "18");
//        redisTemplate.opsForValue().set("lisi", user);
//        User lisi = redisTemplate.opsForValue().get("lisi");
//        System.out.println(lisi);
//    }


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Test
    public void stringRedisTemplate() {
        // 对象
        User user = new User("李四", "18");
        redisTemplate.opsForValue().set("lisi", user);
        User lisi = (User)redisTemplate.opsForValue().get("lisi");
        System.out.println(lisi);
    }


}
