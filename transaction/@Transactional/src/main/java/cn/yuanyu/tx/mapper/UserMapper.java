package cn.yuanyu.tx.mapper;

import cn.yuanyu.tx.entity.User;


public interface UserMapper {
    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 通过用户名删除数据
     *
     * @param username 用户名
     * @return 影响行数
     */
    int deleteByUsername(String username);

    /**
     * 通过用户名查询单条数据
     *
     * @param username 用户名
     * @return 实例对象
     */
    User queryByUsername(String username);


    /**
     * 通过用户名修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int updateByUsername(User user);



    /**
     * 清空表
     */
    void clear();

}