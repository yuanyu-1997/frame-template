package com.nobug;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * https://www.jb51.net/article/168401.htm
 * https://www.cnblogs.com/xd502djj/p/10940627.html
 */

/**
 * http://localhost:6969/static/log-datalist.html
 * @author yuanyu
 */
@EnableJpaAuditing
@SpringBootApplication
public class UserApi {
    public static void main(String[] args) {
        new SpringApplicationBuilder(UserApi.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
