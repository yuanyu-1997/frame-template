//package cn.yuanyu.tx.service.impl;
//
//import cn.yuanyu.tx.entity.User;
//import cn.yuanyu.tx.service.UserService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class CGLIBServiceTest {
//    @Autowired
//    private UserService userService;
//    /**
//     * 测试数据的id
//     */
//    private static final long opId = 1;
//    @Autowired
//    CGLIBServiceImpl cglibService;
//    //springboot 默认使用的代理是cglib代理
//    //@Autowired
//    //UserServiceImpl u;
//
//    @Test
//    public void CGLIBa(){
//        User user = userService.queryById(opId);
//        //年龄减一
//        user.setAge(user.getAge() - 1);
//        cglibService.a(user);
//    }
//
//    @Test
//    public void CGLIBf(){
//        User user = userService.queryById(opId);
//        //年龄减一
//        user.setAge(user.getAge() - 1);
//        cglibService.f(user);
//    }
//}