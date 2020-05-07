package com.yuanyu.multids.aop.datasource;

/**
 * 动态数据源上下文
 *
 * @author yuanyu
 */
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<DataSourceName> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDataSourceName(DataSourceName name) {
        CONTEXT_HOLDER.set(name);
    }


    public static DataSourceName getDataSourceName() {
        return CONTEXT_HOLDER.get();
    }

    /**
     *
     */
    public static void clearDataSourceName() {
        CONTEXT_HOLDER.remove();
    }

}

