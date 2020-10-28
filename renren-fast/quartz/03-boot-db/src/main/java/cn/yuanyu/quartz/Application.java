package cn.yuanyu.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yuanyu
 */
// ~\quartz-x.x.x.jar!\org\quartz\impl\jdbcjobstore\tables_mysql_innodb.sql
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
