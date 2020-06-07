package cn.yuanyu.tx.entity;


import lombok.Data;

/**
 * @author yuanyu
 */
@Data
public class Account {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 金额
     */
    private Float money;
}