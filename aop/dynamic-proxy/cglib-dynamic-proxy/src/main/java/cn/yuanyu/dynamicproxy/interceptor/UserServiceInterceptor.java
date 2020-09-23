package cn.yuanyu.dynamicproxy.interceptor;


import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class UserServiceInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("args:{}", Arrays.toString(args));
        log.info("CGLIB动态代理类前置增强>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // 注意这里的方法调用，不是用反射
        Object result = methodProxy.invokeSuper(obj, args);
        log.info("CGLIB动态代理类后置增强>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return result;
    }
}
