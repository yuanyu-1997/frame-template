

package cn.yuanyu.log.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author yuanyu
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    /**
     * 用户操作描述
     */
    String value() default "";
}
