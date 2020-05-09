package cn.yuanyu.mp.injectors;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;


/**
 * ISqlInjector
 * DefaultSqlInjector
 * 默认的SQL注入器（DefaultSqlInjector）不在注入容器了，所有我们这里直接实现DefaultSqlInjector而不是AbstractSqlInjector
 *
 * @author yuanyu
 */
public class MySqlInjector extends DefaultSqlInjector {

    /**
     *
     */
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        // 获取父类中的集合
        List<AbstractMethod> suList = super.getMethodList(mapperClass);
        // 在扩充之定义的方法
        suList.add(new FindAll());
        return suList;
    }
}
