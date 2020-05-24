package cn.yuanyu.multids.aop.datasource;

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
    @Pointcut("@annotation(cn.yuanyu.multids.aop.datasource.TargetDataSource)")
    public void dataSourcePointCut() { }

    //根据方法上的注解动态的设置是主数据库还是写数据库
    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        TargetDataSource dataSource = method.getAnnotation(TargetDataSource.class);
        if (dataSource == null) {
            //
            log.info("dataSource为空，使用默认的dataSource");
            DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.WRITE);
        } else {
            log.debug("dsType: {}",dataSource.type());
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.type());
        }
        try {
            return point.proceed();
        } finally {
            DynamicDataSourceContextHolder.clearDataSourceName();
        }
    }
}
