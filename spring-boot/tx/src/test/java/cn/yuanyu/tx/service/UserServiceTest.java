package cn.yuanyu.tx.service;

import cn.yuanyu.tx.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    /**
     * 测试数据的id
     */
    private static final long opId = 1;

    @Before
    public void before() {
        System.out.println("\n-------------------------------- before --------------------------------");
        User user = userService.queryById(opId);
        System.out.println(user);
    }

    @After
    public void after() {
        System.out.println("\n-------------------------------- after --------------------------------");
        User user = userService.queryById(opId);
        System.out.println(user);
    }
    //---------------------------------------------------------------------//



    /**
     * 正常执行
     */
    @Test
    public void ok() {
        User user = userService.queryById(opId);
        //年龄减一
        user.setAge(user.getAge() - 1);
        userService.ok(user);
    }


    /**
     * RuntimeException 回滚
     */
    @Test
    public void a() {
        User user = userService.queryById(opId);
        //年龄减一
        user.setAge(user.getAge() - 1);
        userService.a(user);
    }

    /**
     * Error 也是会回滚的
     */
    @Test
    public void b() {
        User user = userService.queryById(opId);
        //年龄减一
        user.setAge(user.getAge() - 1);
        userService.b(user,true);
    }


    /**
     * 默认 IOException 不会回滚
     */
    @Test
    public void c() throws IOException {
        User user = userService.queryById(opId);
        //年龄减一
        user.setAge(user.getAge() - 1);
        userService.c(user);
    }


    /**
     * 设置 IOException 回滚
     */
    @Test
    public void d() throws IOException {
        User user = userService.queryById(opId);
        //年龄减一
        user.setAge(user.getAge() - 1);
        userService.d(user);
    }


    /**
     * TODO 非事务方法(f)调用事务方法(g)，事务方法是没有生效的
     */
    @Test
    public void fg(){
        User user = userService.queryById(opId);
        //年龄减一
        user.setAge(user.getAge() - 1);
        userService.f(user);
    }

    /**
     * 非事务方法(h)调用事务方法(i), 两个方法不在同一个Service里面  事务生效
     */
    @Test
    public void h(){
        User user = userService.queryById(opId);
        //年龄减一
        user.setAge(user.getAge() - 1);
        userService.h(user);
    }



}