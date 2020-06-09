package com.nobug.aop;

import java.lang.annotation.*;

/**
 * 标识当前方法需要被记录日志
 *
 * @author yuanyu
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperateLog {
}
