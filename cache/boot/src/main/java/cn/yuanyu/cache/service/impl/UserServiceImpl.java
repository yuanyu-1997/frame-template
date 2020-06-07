package cn.yuanyu.cache.service.impl;

import cn.yuanyu.cache.entity.User;
import cn.yuanyu.cache.enums.CacheNames;
import cn.yuanyu.cache.mapper.UserMapper;
import cn.yuanyu.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;


/**
 * @author yuanyu
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    /**
     * 插入一条记录
     */
    //TODO 缓存的是方法的返回值
    //@Cacheable(value = CacheNames.USER_NAMESPACE, key = "#user.id")
    //@Override
    //public boolean insert(User user) {
    //    return userMapper.insert(user) > 0;
    //}


    /**
     * 通过主键删除数据
     */
    @Override
    @CacheEvict(value = CacheNames.USER_NAMESPACE, key = "#id")
    public boolean deleteById(Long id) {
        return userMapper.deleteById(id) > 0;
    }


    /**
     * 根据条件更新用户
     */
    //返回值为空，依然不会更新缓存
    //@CachePut(value = CacheNames.USER_NAMESPACE, key = "#user.id")
    //清空缓存（根据id修改可以只清空这一条记录的缓存）
    @CacheEvict(value = CacheNames.USER_NAMESPACE, allEntries = true)
    @Override
    public void update(User user) {
        //...
    }

    /**
     * 根据id修改用户
     */
    @CacheEvict(value = CacheNames.USER_NAMESPACE, key = "#user.id")
    @Override
    public void updateById(User user) {
        //...
    }



    /**
     * 通过ID查询单条数据
     */
    @Override
    @Cacheable(value = CacheNames.USER_NAMESPACE, key = "#id")
    public User selectById(Long id) {
        return userMapper.selectById(id);
    }

    /**
     * TODO 批量删除是没有注解的 需要直接清空所有缓存数据
     * 根据id数组批量删除
     */
    @Override
    @CacheEvict(value = CacheNames.USER_NAMESPACE, allEntries = true)
    public void delete(Integer[] ids) {
        userMapper.deleteBatchIds(Arrays.asList(ids));
    }

    //根据条件删除（同理，直接清空所有缓存）
    //public boolean removeByMap(Map<String,Object> columnMap)
}