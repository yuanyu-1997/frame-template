package com.example.employee;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author baker.yuan
 */
@Repository
public class EmployeeRepository {

    static Map<String, Employee> employeeData;

    static {
        employeeData = new HashMap<>();
        employeeData.put("1", new Employee("1", "1"));
        employeeData.put("2", new Employee("2", "2"));
        employeeData.put("3", new Employee("3", "3"));
        employeeData.put("4", new Employee("4", "4"));
        employeeData.put("5", new Employee("5", "5"));
        employeeData.put("6", new Employee("6", "6"));
        employeeData.put("7", new Employee("7", "7"));
        employeeData.put("8", new Employee("8", "8"));
        employeeData.put("9", new Employee("9", "9"));
        employeeData.put("10", new Employee("10", "10"));
    }

    /**
     * 通过员工ID查询员工信息
     *
     * @param id id
     * @return Mono<Employee>
     */
    public Mono<Employee> findEmployeeById(String id) {
        return Mono.just(employeeData.get(id));
    }

    /**
     * 返回所有员工信息
     *
     * @return Flux<Employee>
     */
    public Flux<Employee> findAllEmployees() {
        return Flux.fromIterable(employeeData.values());
    }

    /**
     * 通过员工ID修改员工信息
     *
     * @param employee employee
     * @return Mono<Employee>
     */
    public Mono<Employee> updateEmployee(Employee employee) {
        Employee existingEmployee = employeeData.get(employee.getId());
        if (existingEmployee != null) {
            existingEmployee.setName(employee.getName());
        }
        return Mono.just(existingEmployee);
    }
}
