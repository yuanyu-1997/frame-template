package com.nobug.multids.mapper;

import com.nobug.multids.entity.User;
import com.nobug.multids.aop.datasource.DataSourceName;
import com.nobug.multids.aop.datasource.TargetDataSource;

public interface UserMapper {

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    @TargetDataSource(name = DataSourceName.WRITE)
    int insert(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @TargetDataSource(name = DataSourceName.WRITE)
    int deleteById(Integer id);


    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    @TargetDataSource(name = DataSourceName.WRITE)
    int update(User user);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @TargetDataSource(name = DataSourceName.READ)
    User queryById(Integer id);


}
