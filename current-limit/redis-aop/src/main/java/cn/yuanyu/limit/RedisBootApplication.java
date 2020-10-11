package cn.yuanyu.limit;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

//docker run \
//--name redis_current_limit \
//-p 6100:6379 \
//-di redis

// docker exec -it redis_current_limit bash
// redis-cli

/**
 * https://el-admin.vip/
 *
 * @author yuanyu
 */
@SpringBootApplication
public class RedisBootApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(RedisBootApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
