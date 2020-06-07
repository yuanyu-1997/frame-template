package cn.yuanyu.easycode.service.impl;

import cn.yuanyu.easycode.mapper.UserMapper;
import cn.yuanyu.easycode.entity.User;
import cn.yuanyu.easycode.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (TbUser)表服务实现类
 *
 * @author makejava
 * @since 2020-05-09 22:46:39
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        userMapper.insert(user);
        return user;
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
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public void update(User user) {
        if (user.getUsername() == null) {
            throw new RuntimeException("用户名不能为空");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //根据用户名来更新
        wrapper.eq("username", user.getUsername());
        userMapper.update(user, wrapper);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long id) {
        return userMapper.selectById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return null;
    }

    /**
     * 全表查询
     */
    @Override
    public List<User> allUser() {
        return userMapper.selectList(null);
    }


}