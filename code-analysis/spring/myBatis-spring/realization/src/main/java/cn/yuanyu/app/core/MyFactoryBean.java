package cn.yuanyu.app.core;


import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

public class MyFactoryBean implements FactoryBean {

    private final Class<?> mapperInterface;

    public MyFactoryBean(Class<?> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object getObject() throws Exception {
//        UserMapper proxy = (UserMapper) new MyMapperProxy(mapperInterface).getProxyInstance();
//        System.out.println("proxy => " + proxy);

        return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{mapperInterface}, new MyMapperProxy());
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }
}
