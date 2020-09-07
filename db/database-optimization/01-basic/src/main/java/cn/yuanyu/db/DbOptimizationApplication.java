package cn.yuanyu.db;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;



@SpringBootApplication
public class DbOptimizationApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(DbOptimizationApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
