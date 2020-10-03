package cn.yuanyu.app.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface AccountMapper {

    @Select("SELECT * FROM account WHERE id = #{accountId}")
    List<Map<String, String>> getAccountIdByAccountId(@Param("accountId") String accountId);
    
} 