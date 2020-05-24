package cn.yuanyu.tx.service;

import cn.yuanyu.tx.entity.User;

import java.io.IOException;
import java.util.List;


/**
 * @author yuanyu
 */
public interface UserService {
    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Long id);

    /**
     * 查询所有
     */
    List<User> queryAll();

    //---------------------------------------------------------------------//

    /**
     * 正常执行
     */
    void ok(User user);

    /**
     * RuntimeException 回滚
     */
    void a(User user);

    /**
     * Error 也是会回滚的
     */
    void b(User user, Boolean flag);

    /**
     * 默认 IOException 不会回滚
     */
    void c(User user) throws IOException;

    /**
     * 设置 IOException 回滚
     */
    void d(User user) throws IOException;


    /**
     * 非事务方法
     */
    void f(User user);

    /**
     * 事务方法
     */
    void g(User user);

    /**
     * 非事务方法
     */
    void h(User user);
}