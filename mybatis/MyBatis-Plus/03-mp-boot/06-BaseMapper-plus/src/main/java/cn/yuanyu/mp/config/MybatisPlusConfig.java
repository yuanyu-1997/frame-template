package cn.yuanyu.mp.config;

import cn.yuanyu.mp.injectors.MySqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuanyu
 */
@Configuration
@MapperScan("cn.yuanyu.mp.mapper")
public class MybatisPlusConfig {

    /**
     *
     */
    @Bean
    public MySqlInjector mySqlInjector() {
        return new MySqlInjector();
    }


}
