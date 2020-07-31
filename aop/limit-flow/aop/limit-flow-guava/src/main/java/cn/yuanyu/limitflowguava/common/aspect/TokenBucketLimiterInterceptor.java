package cn.yuanyu.limitflowguava.common.aspect;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 令牌桶算法限流拦截器
 *
 * @author yuanyu
 */
@Slf4j
@Aspect
@Component
public class TokenBucketLimiterInterceptor {

    private static final RateLimiter rateLimiter = RateLimiter.create(1);

    @Pointcut("@annotation(cn.yuanyu.limitflowguava.common.annotation.LimitFlow)")
    public void aspect() { }

    @Around("aspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.debug("进入限流器");

        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();

        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;


        log.debug("退出限流器");
        // Join point return value will be lost
        return result;
    }
}