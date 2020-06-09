package cn.yuanyu.aop.service;


import cn.yuanyu.aop.config.AppConfig;
import cn.yuanyu.aop.service.impl.WorldServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloServiceTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        HelloService helloService = ctx.getBean(HelloService.class);
        String hello = helloService.doSomething();
        System.out.println(hello);
        WorldServiceImpl worldService = ctx.getBean(WorldServiceImpl.class);//打断点
        String world = worldService.doSomething();
        System.out.println(world);
    }
}