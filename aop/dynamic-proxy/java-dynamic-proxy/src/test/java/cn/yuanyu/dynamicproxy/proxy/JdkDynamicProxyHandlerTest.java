package cn.yuanyu.dynamicproxy.proxy;


import cn.yuanyu.dynamicproxy.bean.User;
import cn.yuanyu.dynamicproxy.service.UserService;
import cn.yuanyu.dynamicproxy.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyHandlerTest {

    @Test
    public void invoke() {
        User user = User.builder().name("蔡徐坤").address("北京").age(28).build();

        UserService userService = new UserServiceImpl();
        JdkDynamicProxyHandler jdkDynamicProxyHandler = new JdkDynamicProxyHandler(userService);

        UserService proxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(), userService.getClass().getInterfaces(), jdkDynamicProxyHandler);
        proxy.addUser(user);
        User kun = proxy.getUserByUsername("蔡徐坤");
        log.info("res => {}", kun);

        log.info("代理类信息,{}:{}", proxy.getClass().getCanonicalName(), proxy.getClass().getInterfaces());
    }

    ///**
    // * @param loader     用哪个类加载器去加载代理对象
    // * @param interfaces 动态代理类需要实现的接口
    // * @param h          动态代理方法在执行时，会调用h里面的invoke方法去执行
    // * @return 动态代理类
    // */
    //public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) {
    //    return null;
    //}

}