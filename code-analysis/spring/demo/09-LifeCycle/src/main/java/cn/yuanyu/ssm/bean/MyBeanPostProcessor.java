package cn.yuanyu.ssm.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 后置处理器：初始化前后进行处理工作
 */
@Component // 将后置处理器加入到容器中
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * @param bean     刚创建的实例
     * @param beanName 实例在容器中的名字
     * @return 返回bean实例，可以返回原来的对象，也可以返回包装后对象
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println();
    	System.out.println("postProcessBeforeInitialization..." + beanName + "=>" + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization..." + beanName + "=>" + bean);
		System.out.println();
        return bean;
    }

}