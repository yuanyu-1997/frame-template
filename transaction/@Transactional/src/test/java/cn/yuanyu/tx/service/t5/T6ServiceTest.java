package cn.yuanyu.tx.service.t5;

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

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class T6ServiceTest {


    @Autowired
    private T6Service t6Service;

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

    // java.sql.SQLException: Connection is read-only. Queries leading to data modification are not allowed
    @Test//(expected = SQLException.class)
    public void m14() {
        // 插入用户
        userMapper.insert(user);
        try {
            // 通过用户名查找该用户
            User user = t6Service.m14(this.user.getUsername());
        }catch (Exception e){
            e.printStackTrace();
        }

        // 查询是否修改成功
        User res = userMapper.queryByUsername(this.user.getUsername());
        log.info("res => {}", res);
    }




    @Test
    public void m15() {
        // 插入用户
        userMapper.insert(user);
        try {
            // 通过用户名查找该用户
            User user = t6Service.m15(this.user.getUsername());
        }catch (Exception e){
            e.printStackTrace();
        }
        // 查询是否修改成功
        User res = userMapper.queryByUsername(this.user.getUsername());
        log.info("res => {}", res);
    }


}