package cn.yuanyu.studentapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@MapperScan("cn.yuanyu.studentapi.mapper")
public class App {
    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
