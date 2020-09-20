package cn.yuanyu.tx.service.t2.impl;


import cn.yuanyu.tx.entity.User;
import cn.yuanyu.tx.service.t2.T2Service;
import cn.yuanyu.tx.service.t2.T3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class T2ServiceImpl implements T2Service {

    @Autowired
    private T3Service t3Service;

    // 没有事务的方法
    @Override
    public void m3(User user) {
        t3Service.m4(user);
    }


}
