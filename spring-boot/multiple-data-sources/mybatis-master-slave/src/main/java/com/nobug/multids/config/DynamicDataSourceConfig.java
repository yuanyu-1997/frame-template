package com.nobug.multids.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.nobug.multids.aop.datasource.DataSourceName;
import com.nobug.multids.aop.datasource.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanyu
 */
@Configuration
public class DynamicDataSourceConfig {

    /**
     * 主数据库 （写）
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.write")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 从数据库 （读）
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.read")
    public DataSource slaveDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceName.WRITE, masterDataSource);
        targetDataSources.put(DataSourceName.READ, slaveDataSource);
        DynamicDataSource ds = new DynamicDataSource();
        //设置数据源映射
        ds.setTargetDataSources(targetDataSources);
        //设置默认数据源，当无法映射到数据源时会使用默认数据源
        ds.setDefaultTargetDataSource(masterDataSource);
        ds.afterPropertiesSet();
        return ds;
    }

}
