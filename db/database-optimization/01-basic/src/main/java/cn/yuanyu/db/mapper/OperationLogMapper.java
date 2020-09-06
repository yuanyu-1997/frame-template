package cn.yuanyu.db.mapper;

import cn.yuanyu.db.pojo.OperationLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OperationLogMapper {
    /**
     * 保存一条日志记录
     */
    int insert(OperationLog operationLog);

    /**
     * 查询结果列表
     */
    List<OperationLog> findByCondition(Map paramMap);

    /**
     * 获取符合条件的总记录数
     */
    Long countByCondition(Map paramMap);

}
