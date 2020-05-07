package com.nobug.multids.aop.datasource;


import lombok.Getter;

/**
 * @author yuanyu
 */

@Getter
public enum DataSourceName {
    /**
     * 读
     */
    READ,
    /**
     * 写
     */
    WRITE;
}
