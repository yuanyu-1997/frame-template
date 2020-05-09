package cn.yuanyu.mp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author yuanyu
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {
    /**
     * 全表扫描（假设BaseMapper里面没有这种方法，我们需要扩展实现）
     */
    List<T> findAll();

    //扩展其他的方法...
}
