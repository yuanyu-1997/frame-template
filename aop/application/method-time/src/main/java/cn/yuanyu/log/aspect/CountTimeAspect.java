package cn.yuanyu.log.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author yuanyu
 */
@Slf4j
@Aspect
@Component
public class CountTimeAspect {
    @Pointcut("@annotation(cn.yuanyu.log.annotation.CountTime)")
    public void timePointCut() {}

    @Around("timePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = null;
        try {
            long beginTime = System.currentTimeMillis();
            obj = joinPoint.proceed();
            //获取方法名称
            String methodName = joinPoint.getSignature().getName();
            //获取类名称
            String className = joinPoint.getSignature().getDeclaringTypeName();
            log.info("类:[{}]，方法:[{}]耗时时间为:[{}]", className, methodName, System.currentTimeMillis() - beginTime + "毫秒");
        } catch (Throwable tb) {
            log.error(tb.getMessage());
        }
        return obj;
    }

}
