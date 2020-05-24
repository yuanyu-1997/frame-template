package cn.yuanyu.tx.service.impl;

import cn.yuanyu.tx.mapper.UserMapper;
import cn.yuanyu.tx.entity.User;
import cn.yuanyu.tx.service.OtherService;
import cn.yuanyu.tx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;


/**
 * @author yuanyu
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
    @Transactional
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
    public User queryById(Long id) {
        return userMapper.queryById(id);
    }

    /**
     * 查询所有
     */
    @Override
    public List<User> queryAll() {
        return userMapper.queryAll();
    }


    //---------------------------------------------------------------------//

    /**
     * 正常执行
     */
    @Override
    @Transactional
    public void ok(User user) {
        userMapper.update(user);
    }

    /**
     * RuntimeException 回滚
     */
    @Override
    @Transactional
    public void a(User user) {
        userMapper.update(user);
        int a = 1 / 0;
    }

    /**
     * Error 也是会回滚的
     */
    @Override
    @Transactional
    public void b(User user , Boolean flag) {
        if (flag) {
            flag = false;
            userMapper.update(user);
        }
        b(user, flag);
    }


    /**
     * 默认 IOException 不会回滚
     */
    @Override
    @Transactional
    public void c(User user) throws IOException {
        userMapper.update(user);
        throw new IOException("IOException 不回滚鸭");
    }

    /**
     * 设置 IOException 回滚
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void d(User user) throws IOException {
        userMapper.update(user);
        throw new IOException("IOException 显示设置也是会回滚的哟");
    }


    /**
     * TODO 非事务方法
     */
    @Override
    public void f(User user) {
        g(user);
    }

    /**
     * 事务方法
     */
    @Override
    @Transactional
    public void g(User user) {
        userMapper.update(user);
        throw new RuntimeException("非事务方法调用事务方法没有回滚哟");
    }


    @Autowired
    OtherService otherService;

    /**
     * TODO 非事务方法
     */
    @Override
    public void h(User user) {
        //调用另一个Service的事务方法
        //代理对象（Proxy）调用i方法，事务生效
        otherService.i(user);
    }
}