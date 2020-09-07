package cn.yuanyu.robredpacket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://www.cnblogs.com/chenyanbin/p/13587508.html
@SpringBootApplication
@MapperScan("cn.yuanyu.robredpacket.mapper")
public class RobRedPacketRedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(RobRedPacketRedisApplication.class, args);
    }
}
