package cn.yuanyu.uaa.mapper;

import cn.yuanyu.uaa.model.AuthScope;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface AuthScopeMapper extends BaseMapper<AuthScope> {
    /**
     * 通过scopeName查询
     *
     * @param scopeName 可被访问的用户的权限范围，比如：basic、super
     */
    AuthScope selectByScopeName(@Param("scopeName") String scopeName);
}