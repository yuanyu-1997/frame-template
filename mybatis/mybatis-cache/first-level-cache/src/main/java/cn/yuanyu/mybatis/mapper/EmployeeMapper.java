package cn.yuanyu.mybatis.mapper;

import cn.yuanyu.mybatis.domain.Employee;

/**
 * @author yuanyu
 */
public interface EmployeeMapper {
    /**
     * 通过id查询员工
     */
    Employee getEmployeeById(Integer id);

    /**
     * 动态修改员工
     */
    void updateEmployeeIfNecessary(Employee employee);
}