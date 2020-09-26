package cn.yuanyu.ssm.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;


@Component
public class LogPostProcessor implements BeanPostProcessor {
    /**
     * @param bean     刚创建的实例
     * @param beanName 实例在容器中的名字
     * @return 返回bean实例，可以返回原来的对象，也可以返回包装后对象
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 传入bean
        DynamicLogProxy dynamicLogProxy = new DynamicLogProxy(bean);
        Object proxyBean = Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), dynamicLogProxy);
        // 返回的不是原来的bean，而是代理bean
        return proxyBean;
    }
}