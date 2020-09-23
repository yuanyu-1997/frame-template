package cn.yuanyu.tx.service.t2;


import cn.yuanyu.tx.entity.User;

public interface T2Service {
    /**
     * 没有事务的方法
     */
    void m3(User user);
}
