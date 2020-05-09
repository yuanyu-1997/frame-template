package cn.yuanyu.mp.config;

import cn.yuanyu.mp.plugins.MyInterceptor;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanyu
 */
@Configuration
@MapperScan("cn.yuanyu.mp.mapper")
public class MybatisPlusConfig {

    /**
     * 注入自定义的拦截器（插件）
     */
    @Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }


    /**
     * 在MP中提供了对SQL执行的分析的插件，可用作阻断全表更新、删除的操作
     * 注意：该插件仅适用于开发环境，不适用于生产环境（性能问题）
     */
    @Bean
    public SqlExplainInterceptor sqlExplainInterceptor() {
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        // 攻击 SQL 阻断解析器、加入解析链
        sqlParserList.add(new BlockAttackSqlParser());
        sqlExplainInterceptor.setSqlParserList(sqlParserList);
        return sqlExplainInterceptor;
    }


    /**
     * PerformanceInterceptor 官方：“该插件3.2.0以上版本移除推荐使用第三方扩展执行SQL分析打印功能”
     * https://blog.csdn.net/qq_39313596/article/details/100901642
     *
     */
    //@Bean
    //public PerformanceInterceptor performanceInterceptor(){
    //
    //}

    /**
     * 乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }


}
