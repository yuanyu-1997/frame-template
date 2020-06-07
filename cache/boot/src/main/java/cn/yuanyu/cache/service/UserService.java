package cn.yuanyu.cache.service;

import cn.yuanyu.cache.entity.User;


/**
 * @author yuanyu
 */
public interface UserService {



    //User insert(User user);



    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);



    /**
     * 根据条件更新用户
     */
    void update(User user);
    /**
     * 根据主键id更新用户
     */
    void updateById(User user);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User selectById(Long id);


    /**
     * 根据id数组批量删除
     */
    void delete(Integer[] ids);


}