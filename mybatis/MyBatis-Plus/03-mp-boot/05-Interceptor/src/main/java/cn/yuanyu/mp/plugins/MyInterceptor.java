package cn.yuanyu.mp.plugins;


import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * @author yuanyu
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class})})
public class MyInterceptor implements Interceptor {

    /**
     * 拦截方法，具体业务逻辑编写的位置
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }

    /**
     * 创建target对象的代理对象,目的是将当前拦截器加入到该对象中
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 属性设置
     */
    @Override
    public void setProperties(Properties properties) {

    }
}
