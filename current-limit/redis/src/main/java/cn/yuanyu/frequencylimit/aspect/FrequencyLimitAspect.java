package cn.yuanyu.frequencylimit.aspect;

import cn.yuanyu.frequencylimit.annotation.RequestLimit;
import cn.yuanyu.frequencylimit.service.impl.CurrentLimitServiceImpl;
import cn.yuanyu.frequencylimit.util.IPUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * 接口限制访问频率，切面处理类
 *
 * @author yuanyu
 */
@Slf4j
@Aspect
@Component
public class FrequencyLimitAspect {

    @Autowired
    private CurrentLimitServiceImpl currentLimitService;

    @Pointcut("@annotation(cn.yuanyu.frequencylimit.annotation.RequestLimit)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //
        Object target = joinPoint.getTarget();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = target.getClass().getMethod(signature.getName(), signature.getParameterTypes());
        RequestLimit requestLimit = method.getAnnotation(RequestLimit.class);

        // 客户端ip
        String clientIP = IPUtils.getClientIP(request);
        boolean isLimit = currentLimitService.limitSendCount(clientIP, requestLimit.timeout(), requestLimit.count());
        if (!isLimit) {
            //请求的方法名
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = signature.getName();
            log.info("限制访问, ip:{}, method:{}", clientIP, (className + "." + methodName + "()"));

            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("application/json;charset=utf-8");
            HashMap<String, String> res = new HashMap<>();
            res.put("msg", "请求频率过快，拒绝访问当前接口");
            res.put("code", "403");
            response.getWriter().write(mapper.writeValueAsString(res));

            return null;
        } else {
            // 执行方法
            return joinPoint.proceed();
        }
    }
}