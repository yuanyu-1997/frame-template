package cn.yuanyu.multids.aop.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * @author yuanyu
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     *
     */
    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType dsName = DynamicDataSourceContextHolder.getDataSourceType();
        log.debug("dsName:{}", dsName);
        return dsName;
    }
}
