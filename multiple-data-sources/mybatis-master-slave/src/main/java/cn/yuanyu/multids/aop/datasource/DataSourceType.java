package cn.yuanyu.multids.aop.datasource;


import lombok.Getter;

/**
 * @author yuanyu
 */
@Getter
public enum DataSourceType {
    /**
     * 读
     */
    READ,
    /**
     * 写
     */
    WRITE;
}
