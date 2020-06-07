package com.nobug.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 操作日志表
 * @author yuanyu
 */
@Data
@Entity
public class OperationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 操作类
     */
    @Column(length = 200)
    private String operateClass;
    /**
     * 操作方法
     */
    @Column(length = 200)
    private String operateMethod;

    /**
     * 返回值类型
     */
    @Column(length = 200)
    private String returnClass;


    /**
     * 操作用户
     */
    @Column(length = 20)
    private String operateUser;


    /**
     * 操作时间
     */
    @Column(length = 20)
    private Date operateTime;


    /**
     * 参请求参数名及参数值
     */
    @Column(length = 500)
    private String paramAndValue;

    /**
     * 执行方法耗时（单位 ms）
     */
    private Long costTime;

    /**
     * 返回值
     */
    @Column(length = 200)
    private String returnValue;

}
