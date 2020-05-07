package com.yuanyu.multids.aop.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author yuanyu
 */
@Slf4j
@Aspect
@Order(-9999)// 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceChangeAspect {
    @Pointcut("@annotation(com.yuanyu.multids.aop.datasource.TargetDataSource)")
    public void dataSourcePointCut() {
    }

    @Around("execution(* com.yuanyu.multids.service.*.*(..))")
    //@Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.debug("around...");
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        TargetDataSource dataSource = method.getAnnotation(TargetDataSource.class);
        if (dataSource == null) {
            throw new RuntimeException("dataSource不能为空");
        } else {
            log.debug("dataSourceName: {}", dataSource.name());
            DynamicDataSourceContextHolder.setDataSourceName(dataSource.name());
        }
        try {
            return point.proceed();
        } finally {
            DynamicDataSourceContextHolder.clearDataSourceName();
        }
    }
}
