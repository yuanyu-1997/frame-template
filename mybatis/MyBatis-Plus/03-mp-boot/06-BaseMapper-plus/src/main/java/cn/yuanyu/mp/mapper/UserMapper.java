package cn.yuanyu.mp.mapper;


import cn.yuanyu.mp.pojo.User;

/**
 * 注意继承的是MyBaseMapper
 *
 * @author yuanyu
 */
public interface UserMapper extends MyBaseMapper<User> {
    /**
     *
     */
    User findById(Long id);
}
