package cn.yuanyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://blog.csdn.net/starexplode/article/details/81198703
 * http://www.luyixian.cn/java_show_177330.aspx
 * https://blog.csdn.net/qq_40794973/article/details/97967016
 *
 * @author yuanyu
 */
@MapperScan("cn.yuanyu.dao")
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
