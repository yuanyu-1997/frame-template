package cn.yuanyu.tx.service.t3.impl;

import cn.yuanyu.tx.entity.User;
import cn.yuanyu.tx.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
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
public class T4ServiceImplTest {

    @Resource
    private UserMapper userMapper;

    //springboot 默认使用的代理是cglib代理
    @Autowired
    private T4ServiceImpl t4Service;


    @Before
    public void before() {
        int exist = userMapper.deleteByUsername("wangwu");
        if (exist > 0) {
            log.info("删除用户wangwu");
        }
    }

    // 1、无事务管理，不回滚
    @Test
    public void m5() {
        User user = new User("wangwu", "123456", "王五", 28, "wangwu@bug.cn");
        try {
            t4Service.m5(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User res = userMapper.queryByUsername("wangwu");
        log.info("res => {}", res);
        assertNotNull(res);
    }

    // 1、有事务管理，正常回滚
    @Test
    public void m6() {
        User user = new User("wangwu", "123456", "王五", 28, "wangwu@bug.cn");
        try {
            t4Service.m6(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User res = userMapper.queryByUsername("wangwu");
        log.info("res => {}", res);
        assertNull(res);
    }

    // 1、protected 无事务管理
    @Test
    public void m7() {
        User user = new User("wangwu", "123456", "王五", 28, "wangwu@bug.cn");
        try {
            t4Service.m7(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User res = userMapper.queryByUsername("wangwu");
        log.info("res => {}", res);
        assertNotNull(res);
    }

    //...


}
