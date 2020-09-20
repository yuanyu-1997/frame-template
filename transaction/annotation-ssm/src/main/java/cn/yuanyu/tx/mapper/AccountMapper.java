package cn.yuanyu.tx.mapper;

import cn.yuanyu.tx.entity.Account;

/**
 * @author yuanyu
 */
public interface AccountMapper{
    /**
     * 通过用户名查找
     */
    Account findAccountByName(String name);

    /**
     * 通过主键修改记录
     */
    int updateAccountById(Account source);
}
