package cn.yuanyu.tx.service.t4;

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

// 异常测试
// RuntimeException或者Error 的时候才会回滚

//                 Throwable
//                /         \
//               /           \
//             /              \
//          Error           Exception
//           / （运行异常）       \（编译异常，需要显示处理的）
//  RuntimeException         IOException ...
//          /
//   空指针、数组下标越界...
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class T5ServiceTest {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private T5Service t5Service;

    @Before
    public void before() {
        int exist = userMapper.deleteByUsername("zhaoliu");
        if (exist > 0) {
            log.info("删除用户zhaoliu");
        }
    }


    /**
     * 1、Error异常会回滚
     */
    @Test
    public void m11() {
        User user = new User("zhaoliu", "123456", "赵六", 21, "zhaoliu@bug.cn");
        try {
            t5Service.m11(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User res = userMapper.queryByUsername("zhaoliu");
        log.info("res => {}", res);
        assertNull(res);
    }


    /**
     * 2、IOException默认不会回滚
     */
    @Test
    public void m12() {
        User user = new User("zhaoliu", "123456", "赵六", 21, "zhaoliu@bug.cn");
        try {
            t5Service.m12(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User res = userMapper.queryByUsername("zhaoliu");
        log.info("res => {}", res);
        assertNotNull(res);
    }


    /**
     * 3、设置IOException回滚
     */
    @Test
    public void m13() {
        User user = new User("zhaoliu", "123456", "赵六", 21, "zhaoliu@bug.cn");
        try {
            t5Service.m13(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User res = userMapper.queryByUsername("zhaoliu");
        log.info("res => {}", res);
        assertNull(res);
    }


}