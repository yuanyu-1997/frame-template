package cn.yuanyu.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author yuanyu
 */
@Aspect
@Component
public class AuthAspect {

    @Before("execution(* cn.yuanyu.aop.service.impl..*(..))")
    public void authority() {
        System.out.println("执行权限检查...");
    }
}

