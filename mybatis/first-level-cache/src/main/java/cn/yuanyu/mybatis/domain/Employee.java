package cn.yuanyu.mybatis.domain;

import lombok.Data;

/**
 * @author yuanyu
 */
@Data
public class Employee {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 电话
     */
    private String phone;
    /**
     * 薪水
     */
    private Double sal;
    /**
     * 状态
     */
    private String state;
}
