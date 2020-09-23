package cn.yuanyu.log.service.impl;

import cn.yuanyu.log.annotation.SysLog;
import cn.yuanyu.log.service.FirstService;
import cn.yuanyu.log.util.ApplicationContextUtil;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// https://www.jianshu.com/p/5df09b132abd
// https://www.bilibili.com/video/BV13t411C7oQ?from=search&seid=11993110953703361598
@Service("firstService")
public class FirstServiceImpl implements FirstService {


    @Autowired
    public ApplicationContextUtil applicationContextUtil;


    // FirstService.m1 -> FirstService.m2
    @Override
    public void m1() {
        // 本质是当前类调用，而不是代理类去调用
        this.m2();
    }

    // FirstService.m1 -> FirstService.m2   注解不生效
    // SecondService.m3 -> FirstService.m2  注解生效
    @Override
    @SysLog //
    public void m2() {

    }


    // 解决办法1
    @Override
    public void m3() {
        // 从容器中获取
        FirstService firstService = applicationContextUtil.getBean(this.getClass());
        // 调用同一个类中有AOP作用的方法
        firstService.m2();
    }


    // 解决办法2
    @Override
    public void m4() {
        //
        FirstService firstService=(FirstService) AopContext.currentProxy();
        firstService.m2();
    }



}
