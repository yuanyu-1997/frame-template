package com.example.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author baker.yuan
 */
@SpringBootApplication
public class EmployeeReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeReactiveApplication.class, args);

		EmployeeWebClient client = new EmployeeWebClient();
		client.consume();
	}

}
