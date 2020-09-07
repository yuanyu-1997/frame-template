package cn.yuanyu.redisboot;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

//docker run \
//--name redis_redis_template \
//-p 6000:6379 \
//-di redis

// docker exec -it redis_redis_template bash
// redis-cli
@SpringBootApplication
public class RedisBootApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(RedisBootApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
