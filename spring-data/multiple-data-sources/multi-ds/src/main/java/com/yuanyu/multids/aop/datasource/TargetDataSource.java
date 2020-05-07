package com.yuanyu.multids.aop.datasource;

import java.lang.annotation.*;


/**
 * @author yuanyu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface TargetDataSource {
    /**
     * 数据源
     */
    DataSourceName name();
}
