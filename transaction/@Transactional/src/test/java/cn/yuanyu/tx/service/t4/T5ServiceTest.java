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

    // 插入的一条记录
    private final String name = "zhaoliu";
    private final User user = new User(name, "123456", "赵六", 21, "zhaoliu@bug.cn");

    @Before
    public void before() {
        userMapper.clear();
        log.info("清空表数据...");
    }


    /**
     * 1、Error异常会回滚
     */
    @Test
    public void m11() {
        try {
            t5Service.m11(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User res = userMapper.queryByUsername(name);
        log.info("res => {}", res);
        assertNull(res);
    }


    /**
     * 2、IOException默认不会回滚
     */
    @Test
    public void m12() {
        try {
            t5Service.m12(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User res = userMapper.queryByUsername(name);
        log.info("res => {}", res);
        assertNotNull(res);
    }


    /**
     * 3、设置IOException回滚
     */
    @Test
    public void m13() {
        try {
            t5Service.m13(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User res = userMapper.queryByUsername(name);
        log.info("res => {}", res);
        assertNull(res);
    }


    /**

*
     */
    @Test
    public void m14() {
        //
        User m14 = new User("m14", "m14");
        User m15 = new User("m15", "m15");
        try {
            t5Service.m14(m14, m15);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("m14 => {}", userMapper.queryByUsername("m14"));
        log.info("m15 => {}", userMapper.queryByUsername("m15"));
        assertNull(userMapper.queryByUsername("m14"));
        assertNull(userMapper.queryByUsername("m15"));
    }


    @Autowired
    private T5Service2 t5Service2;

    @Test
    public void m16() {
        User m16 = new User("m16", "m16");
        User m17 = new User("m17", "m17");
        try {
            t5Service2.m16(m16, m17);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("m16 => {}", userMapper.queryByUsername("m16"));
        log.info("m17 => {}", userMapper.queryByUsername("m17"));
        assertNull(userMapper.queryByUsername("m16"));
        assertNotNull(userMapper.queryByUsername("m17"));
    }


    // 异常被吃掉了，两个方法都不会回滚
    @Test
    public void m20() {
        String name1 = "m20";
        String name2 = "m21";
        User m20 = new User(name1, name1);
        User m21 = new User(name2, name2);
        try {
            t5Service.m20(m20, m21);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("{} => {}", name1, userMapper.queryByUsername(name1));
        log.info("{}  => {}", name2, userMapper.queryByUsername(name2));
        assertNotNull(userMapper.queryByUsername(name1));
        assertNotNull(userMapper.queryByUsername(name2));
    }





    // 异常被吃掉了，报错
    @Test
    public void m22() {
        String name1 = "m22";
        String name2 = "m23";
        User m22 = new User(name1, name1);
        User m23 = new User(name2, name2);
        try {
            t5Service2.m22(m22, m23);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}