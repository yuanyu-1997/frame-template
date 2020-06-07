package cn.yuanyu.log.mapper;

import cn.yuanyu.log.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author yuanyu
 */
public interface UserMapper extends BaseMapper<UserEntity> {
    /**
     * 通过用户名查询单条数据
     */
    UserEntity queryByUsername(String username);

}