package cn.yuanyu.dynamicproxy.proxy;


import cn.yuanyu.dynamicproxy.bean.User;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

// 业务增强
@Slf4j
public class JdkDynamicProxyHandler implements InvocationHandler {

    /**
     * 被代理类
     */
    private Object assignedObject;


    public JdkDynamicProxyHandler(Object assignedObject) {
        super();
        this.assignedObject = assignedObject;
    }

    /**
     * @param proxy  代理对象，就是newProxyInstance方法的返回对象
     * @param method 调用的方法
     * @param args   方法中的参数
     * @return 执行方法后的返回值
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (args != null && args.length > 0 && args[0] instanceof User) {
            User user = (User) args[0];
            // 前置增强
            log.info("JDK动态代理类前置增强>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            // 对参数进行判断...
            if (user.getName() == null) {
                throw new RuntimeException("用户名不能为空");
            }
            Object result = method.invoke(assignedObject, args);
            // 后置增强
            log.info("JDK动态代理类后置增强>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            return result;
        }
        return new RuntimeException("参数错误");
    }


}
