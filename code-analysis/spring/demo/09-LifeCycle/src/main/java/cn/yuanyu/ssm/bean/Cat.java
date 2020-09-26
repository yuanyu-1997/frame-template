package cn.yuanyu.ssm.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Cat implements InitializingBean, DisposableBean {

    public Cat() {
        System.out.println("cat constructor...");
    }

    /**
     * 销毁方法
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("cat destroy...");
    }

    /**
     * 初始化方法
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("cat afterPropertiesSet...");
    }

}