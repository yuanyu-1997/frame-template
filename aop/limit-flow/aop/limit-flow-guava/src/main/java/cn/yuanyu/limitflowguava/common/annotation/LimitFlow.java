package cn.yuanyu.limitflowguava.common.annotation;

import java.lang.annotation.*;
/**
 * 限流注解
 *
 * @author yuanyu
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitFlow {
    /**
     * 每秒允许多少个请求
     */
    int value() default 1000;
}