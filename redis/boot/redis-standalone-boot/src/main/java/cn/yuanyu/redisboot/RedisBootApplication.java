package cn.yuanyu.redisboot;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 */
@SpringBootApplication
public class RedisBootApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(RedisBootApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
