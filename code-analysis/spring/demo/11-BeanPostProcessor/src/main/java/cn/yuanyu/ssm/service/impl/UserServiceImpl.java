package cn.yuanyu.ssm.service.impl;


import cn.yuanyu.ssm.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
// ApplicationContextAwareProcessor

//InitDestroyAnnotationBeanPostProcessor
//AutowiredAnnotationBeanPostProcessor
//AsyncAnnotationBeanPostProcessor
@Service
public class UserServiceImpl implements UserService, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
