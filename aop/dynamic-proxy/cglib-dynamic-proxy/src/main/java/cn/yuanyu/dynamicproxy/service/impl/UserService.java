package cn.yuanyu.dynamicproxy.service.impl;


import cn.yuanyu.dynamicproxy.interceptor.UserServiceInterceptor;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

public class UserService {

    private void a() {
        System.out.println("private方法被调用...");
    }

    protected void b() {
        System.out.println("protected方法被调用...");
    }

    public static void c() {
        System.out.println("public static方法被调用...");
    }

    private static void d() {
        System.out.println("private static方法被调用...");
    }

    public void e() {
        System.out.println("public方法被调用...");
    }


    @Test
    public void invoke() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new UserServiceInterceptor());
        UserService proxyUserService = (UserService) enhancer.create();
        //proxyUserService.a();
        //proxyUserService.b();
        //proxyUserService.c();
        //proxyUserService.d();
        proxyUserService.e();
    }




}
