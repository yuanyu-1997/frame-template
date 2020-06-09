package cn.yuanyu.mybatis.entity;

import lombok.Data;

/**
 * @author yuanyu
 */
@Data
public class Employee {
    private Integer id;
    private String username;
    private String email;
    private String gender;
    /**
     * 部门
     */
    private Department dept;
}