package cn.yuanyu.tx.service.impl;


import cn.yuanyu.tx.entity.Account;
import cn.yuanyu.tx.mapper.AccountMapper;
import cn.yuanyu.tx.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yuanyu
 */
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
        System.out.println(sourceName + " transfer to " + targetName + " " + money + "元");
        //1根据名称查询转出账户
        Account source = accountMapper.findAccountByName(sourceName);
        //2根据名称查询转入账户
        Account target = accountMapper.findAccountByName(targetName);
        //3转出账户减钱
        source.setMoney(source.getMoney() - money);
        //4转入账户加钱
        target.setMoney(target.getMoney() + money);
        //5更新转出账户
        accountMapper.updateAccountById(source);
        throwException(money); //
        //6更新转入账户
        accountMapper.updateAccountById(target);
    }

    private void throwException(Float money) {
        if (money == 1) {
            throw new RuntimeException("发生异常啦...");
        }
    }
}