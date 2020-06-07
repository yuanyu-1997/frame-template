package cn.yuanyu.cache.service;

import cn.yuanyu.cache.entity.User;

/**
 * @author yuanyu
 */
public interface UserService {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Long id);

}