package cn.yuanyu.mybatis.mapper;

import cn.yuanyu.mybatis.entity.Department;
import cn.yuanyu.mybatis.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeMapperTest {
    @Resource
    private EmployeeMapper employeeMapper;
    @Test
    public void getEmpAndDept() {
        Employee employee = employeeMapper.selectEmployeeById("1");
        System.out.println("\n\n-------------------分割线-------------------\n\n");
        Department dept = employee.getDept();

    }
}