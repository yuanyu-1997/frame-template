package cn.yuanyu.tx.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


/**
 * @author yuanyu
 */
@Configuration
@MapperScan("cn.yuanyu.tx.mapper")
@EnableTransactionManagement // TODO 开启事务注解，等同于配置文件<tx:annotation-driven/>
public class MybatisPlusConfiguration {

    /**
     * DataSource
     */
    @Bean
    public DataSource dataSource() {
        String url = "jdbc:mysql://121.36.33.154:5000/tx?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8";
        String username = "root";
        String password = "123456";
        String driverClassName = "com.mysql.cj.jdbc.Driver";

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 设置实体包的别名
        sqlSessionFactoryBean.setTypeAliasesPackage("cn.yuanyu.tx.entity");
        // 指定mapper映射文件的路径
        PathMatchingResourcePatternResolver classPathResource = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(classPathResource.getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean;
    }

    /**
     * TransactionManager
     */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
