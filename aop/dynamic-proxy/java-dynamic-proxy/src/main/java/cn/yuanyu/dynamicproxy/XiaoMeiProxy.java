package cn.yuanyu.dynamicproxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class XiaoMeiProxy implements InvocationHandler {

    private final Girl girl;

    public XiaoMeiProxy(Girl girl) {
        super();
        this.girl = girl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doSomeThingBefore();
        Object ret = method.invoke(girl, args);
        doSomeThingEnd();
        return ret;
    }
    private void doSomeThingBefore() {
        System.out.println("小美的父母说：我得先调查下这个男孩子的背景！");
    }
    private void doSomeThingEnd() {
        System.out.println("小美的父母说：他有没有对你动手动脚啊？");
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(girl.getClass().getClassLoader(), girl.getClass().getInterfaces(), this);
    }

}
