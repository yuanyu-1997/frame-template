package cn.yuanyu.multids.aop.datasource;

/**
 * 动态数据源上下文
 *
 * @author yuanyu
 */
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<DataSourceType> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDataSourceType(DataSourceType name) {
        CONTEXT_HOLDER.set(name);
    }

    public static DataSourceType getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    /**
     *
     */
    public static void clearDataSourceName() {
        CONTEXT_HOLDER.remove();
    }
}

