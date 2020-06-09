package cn.yuanyu.log.service.impl;


import cn.yuanyu.log.entity.SysLogEntity;
import cn.yuanyu.log.mapper.SysLogMapper;
import cn.yuanyu.log.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuanyu
 */
@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;


    /**
     * 保存一条记录
     */
    @Override
    public void save(SysLogEntity sysLog) {
        sysLogMapper.insert(sysLog);
    }
}
