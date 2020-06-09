package cn.yuanyu.log.service;


import cn.yuanyu.log.entity.SysLogEntity;

/**
 * @author yuanyu
 */
public interface SysLogService {
    /**
     * 保存一条记录
     */
    void save(SysLogEntity sysLog);
}
