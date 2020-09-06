package cn.yuanyu.db.aop;


import cn.yuanyu.db.pojo.OperationLog;
import cn.yuanyu.db.service.OperationLogService;
import cn.yuanyu.db.utils.DataUtils;
import cn.yuanyu.db.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 通知类
 *
 * @author yuanyu
 */
@Slf4j
@Aspect
@Component
public class OperateAdvice {

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 拦截 controller 包下所有带有 @OperateLog 注解的方法方法
     */
    @Around("execution(* cn.yuanyu.db.controller.*.*(..)) && @annotation(operateLog)")
    public Object insertLogAround(ProceedingJoinPoint pjp, OperateLog operateLog) throws Throwable {
        System.out.println(" *********************************** 记录日志 [start]  ****************************** ");
        OperationLog op = new OperationLog();
        // 操作用户
        // TODO 实际上应该从session中获取当前登录用户
        op.setOperateUser(DataUtils.getRandStr(8));
        // 操作类
        op.setOperateClass(pjp.getTarget().getClass().getName());
        // 操作方法
        op.setOperateMethod(pjp.getSignature().getName());
        // 参请求参数名及参数值
        op.setParamAndValue(Arrays.toString(pjp.getArgs()));
        long startTime = System.currentTimeMillis();
        // 放行
        Object object = pjp.proceed();
        long endTime = System.currentTimeMillis();
        // 执行方法耗时（单位 ms）
        op.setCostTime(endTime - startTime);
        if (object != null) {
            // 返回值类型
            op.setReturnClass(object.getClass().getName());
            // 返回值
            op.setReturnValue(object.toString());
        } else {
            op.setReturnClass("java.lang.Object");
            op.setReturnValue("void");
        }
        log.error(JsonUtils.obj2JsonString(op));
        operationLogService.insert(op);
        System.out.println(" *********************************** 记录日志 [end]  ****************************** ");
        return object;
    }
}
