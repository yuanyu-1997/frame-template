package com.nobug.multids.aop.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author yuanyu
 */
@Slf4j
@Aspect
@Component
public class DynamicDataSourceChangeAspect {
    @Pointcut("@annotation(com.nobug.multids.aop.datasource.TargetDataSource)")
    public void dataSourcePointCut() { }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        TargetDataSource dataSource = method.getAnnotation(TargetDataSource.class);
        if (dataSource == null) {
            log.info("dataSource为空，使用默认的dataSource");
            DynamicDataSourceContextHolder.setDataSourceName(DataSourceName.WRITE);
        } else {
            log.debug("dataSourceName: {}",dataSource.name());
            DynamicDataSourceContextHolder.setDataSourceName(dataSource.name());
        }
        try {
            return point.proceed();
        } finally {
            DynamicDataSourceContextHolder.clearDataSourceName();
        }
    }
}
