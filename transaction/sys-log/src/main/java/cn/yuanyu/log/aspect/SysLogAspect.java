

package cn.yuanyu.log.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * 系统日志，切面处理类
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {


    @Pointcut("@annotation(cn.yuanyu.log.annotation.SysLog)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();

        //log.info("进入方法:{}", signature.getDeclaringTypeName() + signature.getName());
        System.out.println("进入方法 => " + signature.getDeclaringTypeName() + signature.getName());

        Object res = point.proceed();

        //log.info("离开方法:{}", signature.getDeclaringTypeName() + signature.getName());
        System.out.println("离开方法 => " + signature.getDeclaringTypeName() + signature.getName());

        return res;
    }

}
