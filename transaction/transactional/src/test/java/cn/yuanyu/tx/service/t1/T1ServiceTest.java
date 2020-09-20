package cn.yuanyu.tx.service.t1;

import cn.yuanyu.tx.entity.User;
import cn.yuanyu.tx.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class T1ServiceTest {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private T1Service t1Service;

    /**
     * 测试同一个类中没有事务的方法调用有事务的方法，事务不生效
     */
    // 同一个类中 没有事务方法 => 有事务方法
    @Test
    public void m1() {
        int exist = userMapper.deleteByUsername("zhangsan");
        if (exist > 0) {
            log.info("删除用户zhangsan");
        }
        User user = new User("zhangsan", "123456", "张三", 18, "zhangsan@bug.cn");
        try {
            //
            t1Service.m1(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User res = userMapper.queryByUsername("zhangsan");
        log.info("res => {}", res);
        assertNotNull(res);
    }
}