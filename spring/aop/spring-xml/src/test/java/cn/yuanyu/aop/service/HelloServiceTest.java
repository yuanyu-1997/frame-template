package cn.yuanyu.aop.service;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloServiceTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        HelloService helloService = ctx.getBean(HelloService.class);
        String res = helloService.doSomething();
        System.out.println(res);
    }
}