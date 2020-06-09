package cn.yuanyu.log.service;

import cn.yuanyu.log.entity.UserEntity;

import java.util.List;


/**
 * @author yuanyu
 */
public interface UserService {
    /**
     * 新增数据
     *
     * @param userEntity 实例对象
     * @return 实例对象
     */
    UserEntity insert(UserEntity userEntity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);


    /**
     * 修改数据
     *
     * @param userEntity 实例对象
     * @return 实例对象
     */
    void updateByUsername(UserEntity userEntity);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserEntity queryById(Long id);

    /**
     * 通过用户名查询单条数据
     */
    UserEntity queryByUsername(String username);

    /**
     * 全表查询
     */
    List<UserEntity> allUser();
}