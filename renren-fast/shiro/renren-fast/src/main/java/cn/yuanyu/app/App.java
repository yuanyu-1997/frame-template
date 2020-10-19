package cn.yuanyu.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author yuanyu
 */
@SpringBootApplication
@MapperScan("cn.yuanyu.app.mapper")
public class App {
    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
