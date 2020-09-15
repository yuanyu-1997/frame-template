package cn.yuanyu.frequencylimit.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestLimit {
    /**
     * 时间段，单位为毫秒，默认值一分钟
     */
    long timeout() default 60000;
    /**
     * 允许访问的次数，默认值MAX_VALUE
     */
    int count() default Integer.MAX_VALUE;
}
