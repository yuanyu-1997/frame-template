package cn.yuanyu.ssm.config;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


@Slf4j
public class DynamicLogProxy implements InvocationHandler {
    // 需要代理的对象类
    private final Object target;
    public DynamicLogProxy(Object target) {
        this.target = target;
    }
    /**
     * @param obj    被代理对象
     * @param method 对象方法
     * @param args   方法参数
     */
    @Override
    public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
        // 不做处理
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        // 这里还要根据实际情况判断，不是所有的都需要记录日志
        log.info("记录日志等...");
        // 使用方法的反射
        Object invoke = method.invoke(target, args);
        // 其他操作...
        return invoke;
    }
}