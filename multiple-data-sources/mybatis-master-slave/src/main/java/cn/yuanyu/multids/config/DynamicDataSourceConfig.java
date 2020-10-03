package cn.yuanyu.multids.config;

import cn.yuanyu.multids.aop.datasource.DataSourceType;
import cn.yuanyu.multids.aop.datasource.DynamicDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
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
        targetDataSources.put(DataSourceType.WRITE, masterDataSource);
        targetDataSources.put(DataSourceType.READ, slaveDataSource);

        DynamicDataSource ds = new DynamicDataSource();
        // 设置数据源映射
        ds.setTargetDataSources(targetDataSources);
        // 设置默认数据源，当无法映射到数据源时会使用默认数据源
        ds.setDefaultTargetDataSource(masterDataSource);

        //ds实现了InitializingBean接口的，下面方法其实是会直动调用的
        // ds.afterPropertiesSet();
        return ds;
    }

}
