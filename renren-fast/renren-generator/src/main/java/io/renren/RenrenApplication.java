package io.renren;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// http://localhost:6969/

@SpringBootApplication
@MapperScan("io.renren.dao")
public class RenrenApplication {
	public static void main(String[] args) {
		SpringApplication.run(RenrenApplication.class, args);
	}
}
