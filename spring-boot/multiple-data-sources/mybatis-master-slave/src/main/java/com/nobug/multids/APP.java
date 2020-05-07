package com.nobug.multids;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * https://www.jb51.net/article/120869.htm
 * https://blog.csdn.net/Dome_/article/details/103150846
 */

/**
 * @author yuanyu
 */
@MapperScan(basePackages = "com.nobug.multids.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class APP {
    public static void main(String[] args) {
        new SpringApplicationBuilder(APP.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
