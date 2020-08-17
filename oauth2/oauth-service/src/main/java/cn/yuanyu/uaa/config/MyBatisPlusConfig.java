package cn.yuanyu.uaa.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.yuanyu.uaa.mapper")
public class MyBatisPlusConfig {
}
