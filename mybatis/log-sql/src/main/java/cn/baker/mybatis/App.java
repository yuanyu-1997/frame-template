package cn.baker.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author yuanyu
 */
// https://blog.csdn.net/weixin_33856370/article/details/92392377
// https://blog.csdn.net/qq_40794973/article/details/104831325
// https://www.jb51.net/article/197091.htm
    //https://blog.csdn.net/A169388842/article/details/106574429
@SpringBootApplication
@MapperScan("cn.baker.mybatis.mapper")
public class App {
    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
