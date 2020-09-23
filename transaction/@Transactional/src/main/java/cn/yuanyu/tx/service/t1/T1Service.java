package cn.yuanyu.tx.service.t1;

import cn.yuanyu.tx.entity.User;

public interface T1Service {
    /**
     * 没有事务的方法
     */
    void m1(User user);
    /**
     * 有事务的方法
     */
    void m2(User user);
}
