package com.example.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author baker.yuan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String id;
    private String name;
}
