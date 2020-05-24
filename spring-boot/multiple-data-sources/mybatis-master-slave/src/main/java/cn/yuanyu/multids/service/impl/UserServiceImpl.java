package cn.yuanyu.multids.service.impl;


import cn.yuanyu.multids.entity.User;
import cn.yuanyu.multids.mapper.UserMapper;
import cn.yuanyu.multids.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author yuanyu
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 用户注册
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public User register(User user) {
        userMapper.insert(user);
        //int a = 1 / 0;
        return user;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return userMapper.deleteById(id) > 0;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        userMapper.update(user);
        return queryById(user.getId());
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return userMapper.queryById(id);
    }

}