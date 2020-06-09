package cn.yuanyu.log.service.impl;

import cn.yuanyu.log.mapper.UserMapper;
import cn.yuanyu.log.entity.UserEntity;
import cn.yuanyu.log.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 新增数据
     *
     * @param userEntity 实例对象
     * @return 实例对象
     */
    @Override
    public UserEntity insert(UserEntity userEntity) {
        userMapper.insert(userEntity);
        return userEntity;
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return userMapper.deleteById(id) > 0;
    }

    /**
     * 修改数据
     *
     * @param userEntity 实例对象
     * @return 实例对象
     */
    @Override
    public void updateByUsername(UserEntity userEntity) {
        if (userEntity.getUsername() == null) {
            throw new RuntimeException("用户名不能为空");
        }
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        //根据用户名来更新
        wrapper.eq("username", userEntity.getUsername());
        userMapper.update(userEntity, wrapper);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserEntity queryById(Long id) {
        return userMapper.selectById(id);
    }
    /**
     * 通过用户名查询单条数据
     */
    @Override
    public UserEntity queryByUsername(String username) {
        return userMapper.queryByUsername(username);
    }


    /**
     * 全表查询
     */
    @Override
    public List<UserEntity> allUser() {
        return userMapper.selectList(null);
    }

}