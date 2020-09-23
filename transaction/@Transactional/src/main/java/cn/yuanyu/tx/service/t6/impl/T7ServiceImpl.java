package cn.yuanyu.tx.service.t6.impl;

import cn.yuanyu.tx.entity.User;
import cn.yuanyu.tx.service.t6.T7Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

// https://blog.csdn.net/u010266988/article/details/88388832
@Slf4j
@Service
public class T7ServiceImpl implements T7Service {

    @Override
    @Transactional
    public void m17(User user) {
        String txName = TransactionSynchronizationManager.getCurrentTransactionName();
        log.info("txName:{}", txName);
    }

    @Override
    public void m18(User user) {

    }

    @Override
    public void m19(User user) {

    }


}
