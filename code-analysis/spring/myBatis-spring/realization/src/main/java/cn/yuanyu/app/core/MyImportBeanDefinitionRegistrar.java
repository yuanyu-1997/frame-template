package cn.yuanyu.app.core;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;


public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(MyFactoryBean.class);
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue("cn.yuanyu.app.mapper.UserMapper");

        registry.registerBeanDefinition("userMapper", beanDefinition);
    }
}
