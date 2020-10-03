package cn.yuanyu.multids.aop.datasource;

import java.lang.annotation.*;

/**
 * @author yuanyu
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    /**
     * 读库还是写库
     */
    DataSourceType type();
}
