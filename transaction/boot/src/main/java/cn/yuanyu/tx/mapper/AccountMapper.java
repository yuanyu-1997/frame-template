package cn.yuanyu.tx.mapper;

import cn.yuanyu.tx.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author yuanyu
 */
public interface AccountMapper extends BaseMapper<Account> {
    /**
     * 通过用户名查找
     */
    Account findAccountByName(String name);
}