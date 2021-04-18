package com.example.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author baker.yuan
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;


    /**
     * 通过员工ID查询员工信息
     */
    @GetMapping("/{id}")
    private Mono<Employee> getEmployeeById(@PathVariable String id) {
        return employeeRepository.findEmployeeById(id);
    }

    /**
     * 返回所有员工信息
     */
    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    private Flux<Employee> getAllEmployees() {
        return employeeRepository.findAllEmployees()
                .delayElements(Duration.ofMillis(100))
                .doOnNext(employee -> System.out.println(System.currentTimeMillis() + " <= Server produces:" + employee));
    }

    /**
     * 通过员工ID修改员工信息
     */
    @PostMapping("/update")
    private Mono<Employee> updateEmployee(@RequestBody Employee employee) {
        return employeeRepository.updateEmployee(employee);
    }

}
