package cn.yuanyu.mp.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author yuanyu
 */

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入数据时填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object version = getFieldValByName("version", metaObject);
        if (null == version) {
            //字段为空，可以进行填充
            setFieldValByName("version", 1, metaObject);
        }
    }

    /**
     * 更新数据时填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {

    }
}