package com.yuanyu.multids.config;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.yuanyu.multids.aop.datasource.DataSourceName;
import com.yuanyu.multids.aop.datasource.DynamicDataSource;
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
     * order
     */
    @Bean
    @ConfigurationProperties("spring.datasource.order")
    public DataSource orderDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * user
     */
    @Bean
    @ConfigurationProperties("spring.datasource.user")
    public DataSource userDataSource() {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource orderDataSource, DataSource userDataSource) {
        Map<Object, Object> tds = new HashMap<>();
        tds.put(DataSourceName.USER, userDataSource);
        tds.put(DataSourceName.ORDER, orderDataSource);
        DynamicDataSource ds = new DynamicDataSource();
        //设置数据源映射
        ds.setTargetDataSources(tds);
        //设置默认数据源，当无法映射到数据源时会使用默认数据源
        ds.setDefaultTargetDataSource(userDataSource);
        ds.afterPropertiesSet();
        return ds;
    }

}
