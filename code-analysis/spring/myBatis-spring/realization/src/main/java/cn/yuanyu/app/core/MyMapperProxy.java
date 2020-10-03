package cn.yuanyu.app.core;

import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class MyMapperProxy implements InvocationHandler {

//    public Class<?> tClass;
//
//    public MyMapperProxy(Class<?> tClass) {
//        this.tClass = tClass;
//    }

//    /**
//     * 获取代理对象
//     */
//    public Object getProxyInstance() {
//        return Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{tClass}, this);
//    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String sql = method.getAnnotation(Select.class).value()[0];
        System.out.println("sql => " + sql);

        return null;
    }
}
