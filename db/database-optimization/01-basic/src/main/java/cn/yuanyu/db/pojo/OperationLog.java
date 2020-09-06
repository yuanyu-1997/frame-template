package cn.yuanyu.db.pojo;


import lombok.Data;

import java.util.Date;

/**
 * 操作日志表
 *
 * @author yuanyu
 */
@Data
public class OperationLog {
    private Long id;
    /**
     * 操作类
     */
    private String operateClass;
    /**
     * 操作方法
     */
    private String operateMethod;
    /**
     * 返回值类型
     */
    private String returnClass;
    /**
     * 操作用户
     */
    private String operateUser;
    /**
     * 参请求参数名及参数值
     */
    private String paramAndValue;
    /**
     * 执行方法耗时（单位 ms）
     */
    private Long costTime;
    /**
     * 返回值
     */
    private String returnValue;
    /**
     * 创建时间
     */
    private Date createTime;
}
