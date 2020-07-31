

package io.renren.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus配置
 */
@Configuration
@MapperScan("io.renren.modules.app.dao")
public class MybatisPlusConfig {
}