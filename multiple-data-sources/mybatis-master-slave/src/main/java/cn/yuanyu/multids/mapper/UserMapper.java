package cn.yuanyu.multids.mapper;

import cn.yuanyu.multids.aop.datasource.DataSourceType;
import cn.yuanyu.multids.aop.datasource.TargetDataSource;
import cn.yuanyu.multids.entity.User;

/**
 * @author yuanyu
 */
public interface UserMapper {

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    @TargetDataSource(type = DataSourceType.WRITE)
    int insert(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @TargetDataSource(type = DataSourceType.WRITE)
    int deleteById(Integer id);


    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    @TargetDataSource(type = DataSourceType.WRITE)
    int update(User user);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @TargetDataSource(type = DataSourceType.READ)
    User queryById(Integer id);


}
