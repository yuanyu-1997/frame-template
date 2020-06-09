package cn.yuanyu.mybatis.mapper;


import cn.yuanyu.mybatis.entity.Employee;

/**
 * @author yuanyu
 */
public interface EmployeeMapper {
    /**
     * 通过主键id查找记录
     *
     * @param id 主键id
     * @return 员工
     */
    Employee selectEmployeeById(String id);
}
