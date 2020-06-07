package cn.yuanyu.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * VM options -Dserver.port=8848
 *
 * @author yuanyu
 */
@SpringBootApplication
//TODO
@EnableRedisHttpSession
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
