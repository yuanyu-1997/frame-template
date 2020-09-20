package cn.yuanyu.tx.service;


import cn.yuanyu.tx.entity.Account;

/**
 * 账户的业务层接口
 *
 * @author yuanyu
 */
public interface AccountService {
    /**
     * 根据用户名查询账户信息
     */
    Account findAccountByName(String name);

    /**
     * 转账
     *
     * @param sourceName 转成账户名称
     * @param targetName 转入账户名称
     * @param money      转账金额
     */
    void transfer(String sourceName, String targetName, Float money);
}