package cn.yuanyu.mybatisexecutor.mapper;


import cn.yuanyu.mybatisexecutor.domain.User;

public interface UserMapper {
    /**
     * 清空表
     */
    void clear();
    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);
    /**
     * 新增数据
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);
}
