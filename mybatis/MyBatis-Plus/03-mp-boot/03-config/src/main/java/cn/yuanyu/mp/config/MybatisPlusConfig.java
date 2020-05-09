package cn.yuanyu.mp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuanyu
 */
@Configuration
@MapperScan("cn.yuanyu.mp.mapper") //设置mapper接口的扫描包
public class MybatisPlusConfig {
    ///**
    // * 配置分页插件
    // */
    //@Bean
    //public PaginationInterceptor paginationInterceptor(){
    //    return new PaginationInterceptor();
    //}
}
