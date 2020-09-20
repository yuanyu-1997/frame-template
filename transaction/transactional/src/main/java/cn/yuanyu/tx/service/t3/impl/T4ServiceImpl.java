package cn.yuanyu.tx.service.t3.impl;

import cn.yuanyu.tx.entity.User;
import cn.yuanyu.tx.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

// 只有public方法的事务注解有事务管理，并且必须外部调用

@Slf4j
@Service
public class T4ServiceImpl {

    @Resource
    private UserMapper userMapper;

    // 无事务管理 ×
    @Transactional
    void m5(User user) {
        userMapper.insert(user);
        int a = 1 / 0;
    }

    /**
     * 正常执行 有事务管理 √
     */
    @Transactional
    public void m6(User user) {
        userMapper.insert(user);
        int a = 1 / 0;
    }


    /**
     * protected 无事务管理 ×
     */
    @Transactional
    protected void m7(User user) {
        userMapper.insert(user);
        int a = 1 / 0;
    }

    ///**
    // * private 编译器就会报错
    // */
    //@Transactional
    //private void b(User user) {
    //    userMapper.update(user);
    //    int a = 1 / 0;
    //}


    //@Transactional
    //public final void c(User user) {
    //    userMapper.update(user);
    //    int a = 1 / 0;
    //}

    //@Transactional
    //public static void e(User user) {
    //    //userMapper.update(user);
    //    int a = 1 / 0;
    //}


}
