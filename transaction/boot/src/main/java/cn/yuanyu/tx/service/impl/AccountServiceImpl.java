package cn.yuanyu.tx.service.impl;

import cn.yuanyu.tx.entity.Account;
import cn.yuanyu.tx.mapper.AccountMapper;
import cn.yuanyu.tx.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事务控制应该都是在业务层
 *
 * @author yuanyu
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account findAccountByName(String name) {
        return accountMapper.findAccountByName(name);
    }

    @Override
    @Transactional
    public void transfer(String sourceName, String targetName, Float money) {
        log.info("{} transfer to {} {}元", sourceName, targetName, money);
        //1 根据名称查询转出账户
        Account source = accountMapper.findAccountByName(sourceName);
        //2 根据名称查询转入账户
        Account target = accountMapper.findAccountByName(targetName);
        //3 转出账户减钱
        source.setMoney(source.getMoney() - money);
        //4 转入账户加钱
        target.setMoney(target.getMoney() + money);
        //5 更新转出账户
        accountMapper.updateById(source);
        throwException(money); //
        int i = 1 / 0;
        //6 更新转入账户
        accountMapper.updateById(target);
    }

    private void throwException(Float money) {
        if (money == 1) {
            throw new RuntimeException("发生异常啦...");
        }
    }
}
