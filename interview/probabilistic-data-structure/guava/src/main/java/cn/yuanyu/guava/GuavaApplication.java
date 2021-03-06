package cn.yuanyu.guava;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://www.jianshu.com/p/2104d11ee0a2
// https://mp.weixin.qq.com/s/H7Gw-k2laoFnt9cIaiaEcQ

@SpringBootApplication
@MapperScan("cn.yuanyu.guava.mapper")
public class GuavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuavaApplication.class, args);
    }
}
