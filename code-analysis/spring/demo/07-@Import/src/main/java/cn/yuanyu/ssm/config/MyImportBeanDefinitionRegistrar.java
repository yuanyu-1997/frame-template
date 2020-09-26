package cn.yuanyu.ssm.config;

import cn.yuanyu.ssm.bean.Pink;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * BeanDefinitionRegistry.registerBeanDefinition手工注册进来
     *
     * @param importingClassMetadata 当前类的注解信息
     * @param registry               BeanDefinition注册类
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean hasYellow = registry.containsBeanDefinition("cn.yuanyu.ssm.bean.Yellow");
        if (hasYellow) {
            // 指定bean的定义信息（bean的类型等等）
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Pink.class);
            // 注册一个bean，指定bean名
            registry.registerBeanDefinition("pink", beanDefinition);
        }
    }
}
