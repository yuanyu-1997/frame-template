package cn.baker.app.config;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ObjectUtils;

import javax.sql.DataSource;

/**
 * @author yuanyu
 */
@Configuration
@MapperScan(basePackages = "cn.baker.app.mapper.video", sqlSessionTemplateRef = "videoSqlSessionTemplate")
@EnableConfigurationProperties({MybatisProperties.class})
public class VideoDataSourceConfig {

    @Autowired
    private MybatisProperties properties;

    /**
     * 数据源
     */
    //@Primary
    @Bean(name = "videoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.db2")
    public DataSource videoDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "videoSqlSessionFactory")
    public SqlSessionFactory videoSqlSessionFactory(@Qualifier("videoDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            factory.setMapperLocations(this.properties.resolveMapperLocations());
        }
        return factory.getObject();

    }

    /**
     * 配置事务管理
     */
    @Bean(name = "videoDataSourceTransactionManager")
    public DataSourceTransactionManager videoDataSourceTransactionManager(@Qualifier("videoDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "videoSqlSessionTemplate")
    public SqlSessionTemplate videoSqlSessionTemplate(@Qualifier("videoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        ExecutorType executorType = this.properties.getExecutorType();
        if (executorType != null) {
            return new SqlSessionTemplate(sqlSessionFactory, executorType);
        } else {
            return new SqlSessionTemplate(sqlSessionFactory);
        }
    }
}
