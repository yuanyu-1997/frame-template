package com.nobug.vo;

import lombok.Data;

/**
 *
 * @author yuanyu
 */
@Data
public class OperationLogParameter {
    /**
     * 操作用户
     */
    private String operateUser;
    /**
     * 操作方法
     */
    private String operateMethod;
    /**
     * 返回值类型
     */
    private String returnClass;

    /**
     * 执行方法耗时（单位 ms）
     */
    private Long costTime;
}
