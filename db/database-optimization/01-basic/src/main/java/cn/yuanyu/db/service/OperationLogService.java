package cn.yuanyu.db.service;


import cn.yuanyu.db.mapper.OperationLogMapper;
import cn.yuanyu.db.pojo.OperationLog;
import cn.yuanyu.db.vo.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OperationLogService {

    @Resource
    private OperationLogMapper operationLogMapper;

    /**
     * 保存一条日志记录
     */
    public void insert(OperationLog operationLog) {
        operationLogMapper.insert(operationLog);
    }


    /**
     * @param paramMap 查询条件
     * @param pageNum  查询的页码
     * @param rows     每页查询的条数
     */
    public PageResult findByPage(Map<String, Object> paramMap, Integer pageNum, Integer rows) {
        if (paramMap == null) {
            paramMap = new HashMap<>();
        }
        paramMap.put("start", (pageNum - 1) * rows);
        paramMap.put("rows", rows);

        Object costTime = paramMap.get("costTime");
        if (costTime != null) {
            if ("".equals(costTime.toString())) {
                paramMap.put("costTime", null);
            } else {
                paramMap.put("costTime", new Long(costTime.toString()));
            }
        }
        long start_time = System.currentTimeMillis();
        Long count = operationLogMapper.countByCondition(paramMap);
        long end_time = System.currentTimeMillis();
        log.info("count cost time : {} ms", (end_time - start_time));
        List<OperationLog> list = operationLogMapper.findByCondition(paramMap);
        long end_time2 = System.currentTimeMillis();
        log.info("query cost time : {} ms", (end_time2 - end_time));
        return new PageResult(count, list);
    }













}
