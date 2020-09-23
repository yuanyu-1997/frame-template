package cn.yuanyu.tx.service.t3;


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

import static org.junit.Assert.assertNotNull;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class T4Service2Test {

    @Autowired
    private T4Service2 t4Service2;

    @Resource
    private UserMapper userMapper;

    // 插入的一条记录
    private final String name = "zhaoliu";
    private final User user = new User(name, "123456", "赵六", 21, "zhaoliu@bug.cn");

    @Before
    public void before() {
        int exist = userMapper.deleteByUsername(name);
        if (exist > 0) {
            log.info("数据库存在用户{}，删除用户{}", name, name);
        }
    }


    @Test
    public void m20() {
        try {
            t4Service2.m20(user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        User res = userMapper.queryByUsername(name);
        log.info("res => {}", res);
        assertNotNull(res);
    }


}