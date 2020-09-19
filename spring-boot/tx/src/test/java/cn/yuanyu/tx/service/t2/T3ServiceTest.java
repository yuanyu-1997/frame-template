package cn.yuanyu.tx.service.t2;

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
public class T3ServiceTest {
    @Resource
    private UserMapper userMapper;

    @Autowired
    private T2Service t2Service;

    /**
     * 测试不同类中没有事务的方法调用有事务的方法，事务生效
     */
    @Test
    public void m4() {
        int exist = userMapper.deleteByUsername("lisi");
        if (exist > 0) {
            log.info("删除用户lisi");
        }
        User user = new User("lisi", "123456", "李四", 20, "lisi@bug.cn");
        try {
            t2Service.m3(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User res = userMapper.queryByUsername("lisi");
        log.info("res => {}", res);
        assertNull(res);
    }
}