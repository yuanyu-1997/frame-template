package cn.yuanyu.mybatis.entity;

import lombok.Data;

import java.util.List;

/**
 * @author yuanyu
 */
@Data
public class Department {
    private Integer id;
    private String deptName;
    /**
     * 多个员工
     */
    private List<Employee> emps;
}
