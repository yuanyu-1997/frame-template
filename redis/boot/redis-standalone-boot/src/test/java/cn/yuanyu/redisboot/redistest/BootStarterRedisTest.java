package cn.yuanyu.redisboot.redistest;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BootStarterRedisTest {
//    // RedisAutoConfiguration
//
//    // 操作字符串
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    // k v 都是对象
//    @Autowired
//    private RedisTemplate<Object, Object> redisTemplate;
//
//    //redisTemplate.opsForValue();    // 操作字符串（String）
//    //redisTemplate.opsForList();     // 操作列表（List）
//    //redisTemplate.opsForSet();      // 操作集合（Set）
//    //redisTemplate.opsForHash();     // 操作散列（Hash）
//    //redisTemplate.opsForZSet();     // 操作有序（zSet）
//
//    // http://redis.cn/commands.html
//    @Test
//    public void stringRedisTemplate() {
//        // 字符串
//        stringRedisTemplate.opsForValue().set("msg", "hello");
//        String v = stringRedisTemplate.opsForValue().get("msg");
//        System.out.println(v);
//        // 对象
//        User user = new User("李四", "18");
//        redisTemplate.opsForValue().set("lisi", user);
//        User lisi = (User)redisTemplate.opsForValue().get("lisi");
//        System.out.println(lisi);
//    }
}